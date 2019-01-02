package eobrazovanje.tim014.service;

import eobrazovanje.tim014.model.Dokument;
import eobrazovanje.tim014.repository.DokumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DokumentService {

    @Autowired
    DokumentRepo dokumentRepo;

    @Autowired
    FileStorageSevice fileStorageSevice;

    public Dokument getOne(Integer id){
        return dokumentRepo.getOne(id);
    }

    public List<Dokument> getAll(){
        return dokumentRepo.findAll();
    }

    public Dokument save(Dokument dokument){
        return dokumentRepo.save(dokument);
    }

    public void remove(Integer id){
        Dokument dokument = dokumentRepo.getOne(id);
        fileStorageSevice.removeFile(dokument.getDownloadUri());
        dokumentRepo.deleteById(id);

    }


}
