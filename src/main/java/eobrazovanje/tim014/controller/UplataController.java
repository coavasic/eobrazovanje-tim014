package eobrazovanje.tim014.controller;

import eobrazovanje.tim014.dto.UplataDTO;
import eobrazovanje.tim014.model.Uplata;
import eobrazovanje.tim014.repository.StudentRepo;
import eobrazovanje.tim014.service.UplataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UplataController {


    @Autowired
    UplataService uplataService;

    @Autowired
    StudentRepo studentRepo;

    @GetMapping("uplata/{id}")
    public ResponseEntity<UplataDTO> getById(@PathVariable Integer id){
        Uplata uplata = uplataService.getOne(id);
        if(uplata == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UplataDTO>(new UplataDTO(uplata),HttpStatus.OK);
    }

    @GetMapping("uplata/all")
    public ResponseEntity<List<UplataDTO>> getAll(){
        List<Uplata> uplate = uplataService.getAll();
        List<UplataDTO> uplataDTOS = uplate.stream().map(
                uplata -> new UplataDTO(uplata)
        ).collect(Collectors.toList());

        return new ResponseEntity<List<UplataDTO>>(uplataDTOS,HttpStatus.OK);

    }

    @PostMapping("uplata")
    public ResponseEntity<?> add(@RequestBody UplataDTO uplataDTO){
        Uplata uplata = new Uplata();
        uplata.setUplata(uplataDTO.getId());
        uplata.setDatumUplate(uplataDTO.getDatumUplate());
        uplata.setSvrhaUplate(uplataDTO.getSvrhaUplate());
        uplata.setIznos(uplata.getIznos());
        uplata.setStudent(studentRepo.findByBrojIndeksa(uplataDTO.getBrojIndeksa()));

        return new ResponseEntity<UplataDTO>(uplataDTO,HttpStatus.OK);

    }
}
