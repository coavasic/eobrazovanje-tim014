package eobrazovanje.tim014.service;

import eobrazovanje.tim014.exception.FileStorageException;
import eobrazovanje.tim014.exception.MyFileNotFoundException;
import eobrazovanje.tim014.propery.FileStorageProperties;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageSevice {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageSevice(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
    }

    public String storeFiles(MultipartFile file, String username){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")){
                throw new FileStorageException("Invalid filename "  + fileName);
            }
            Path pathWithUsername = Paths.get(this.fileStorageLocation.toString(),username);
            Files.createDirectories(pathWithUsername);
            Path targetLocation = pathWithUsername.resolve(fileName);
            System.out.println(targetLocation);
            Files.copy(file.getInputStream(),targetLocation,StandardCopyOption.REPLACE_EXISTING);
            return username + "/" +fileName;
        } catch (IOException ex){
            throw new FileStorageException(ex.getMessage());

        }
    }

    public Resource loadFileAsResource(String fileName,String username){
        try {
            Path filePath = this.fileStorageLocation.resolve(username).resolve(fileName).normalize();
            System.out.println(filePath.toString());
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex){
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public boolean checkIfFileExist(String fileName){

            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            System.out.println(filePath.toString());
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if(resource.exists()){
                return true;
            }else {
                return false;
            }

    }

    public void removeFile(String fileName){
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        System.out.println(filePath.toString());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    public void deleteFiles(){
        try {
            FileUtils.cleanDirectory(new File(this.fileStorageLocation.normalize().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
