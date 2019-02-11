package eobrazovanje.tim014.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eobrazovanje.tim014.dto.DokumentDTO;
import eobrazovanje.tim014.model.Dokument;
import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.model.Student;
import eobrazovanje.tim014.repository.DokumentRepo;
import eobrazovanje.tim014.repository.KorisnikRepo;
import eobrazovanje.tim014.repository.StudentRepo;
import eobrazovanje.tim014.response.UploadFileResponse;
import eobrazovanje.tim014.service.DokumentService;
import eobrazovanje.tim014.service.FileStorageSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DokumentController {


    //TO DO
    //Use services instead of repos, respect spring architecture

    @Autowired
    DokumentService dokumentService;

    @Autowired
    FileStorageSevice fileStorageSevice;

    @Autowired
    KorisnikRepo korisnikRepo;

    @Autowired
    StudentRepo studentRepo;

    @PostMapping(value = "api/uploadDoc",consumes = "multipart/form-data")
    public UploadFileResponse uploadFile(@RequestParam(value="dokument") String dokumentString, @RequestPart("file") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = korisnikRepo.findByKorisnickoIme(authentication.getName());
        ObjectMapper mapper = new ObjectMapper();
        DokumentDTO dokumentDTO = mapper.readValue(dokumentString,DokumentDTO.class);
        System.out.println(dokumentDTO.toString());
        Student student = null;
        if(korisnik.getTipkorisnika().equals("student")){
            student = studentRepo.findByKorisnickoIme(korisnik.getKorisnickoIme());
        }
        if(korisnik.getTipkorisnika().equals("administrator")){
            student = studentRepo.findByBrojIndeksa(dokumentDTO.getBrojIndeksa());
        }
        Dokument dokument = new Dokument();
        String fileName = fileStorageSevice.storeFiles(file,student.getKorisnickoIme());
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
        dokument.setDownloadUri(fileName);
        dokument.setStudent(student);
        dokument.setNaziv(dokumentDTO.getNaziv());
        dokument.setTipDokumenta(dokumentDTO.getTipDokumenta());
        dokumentService.save(dokument);



        return new UploadFileResponse(fileName,fileDownloadUri,file.getContentType(),file.getSize());

    }

    @GetMapping("/dokument/{id}")
    public ResponseEntity<DokumentDTO> getOne(@PathVariable Integer id){
        Dokument dokument = dokumentService.getOne(id);
        if(fileStorageSevice.checkIfFileExist(dokument.getDownloadUri())){
            DokumentDTO dokumentDTO = new DokumentDTO(dokument);
            return new ResponseEntity<DokumentDTO>(dokumentDTO,HttpStatus.OK);
        } else {
            dokumentService.remove(id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("api/dokument/all")
    public ResponseEntity<List<DokumentDTO>> getAll(){
        List<Dokument> dokuments = dokumentService.getAll();
        List<DokumentDTO> dokumentDTOS = dokuments.stream().filter(
                dokument -> fileStorageSevice.checkIfFileExist(dokument.getDownloadUri())
        ).map(dokument -> new DokumentDTO(dokument)).collect(Collectors.toList());

        return new ResponseEntity<List<DokumentDTO>>(dokumentDTOS,HttpStatus.OK);
    }

    @GetMapping("api/downloadFile/{username}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, @PathVariable String username, HttpServletRequest request){
        Resource resource = fileStorageSevice.loadFileAsResource(fileName,username);
        System.out.println(resource.getFilename());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("api/dokument/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        dokumentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
