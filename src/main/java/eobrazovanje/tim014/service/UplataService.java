package eobrazovanje.tim014.service;

import eobrazovanje.tim014.model.Uplata;
import eobrazovanje.tim014.repository.UplataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UplataService {

    @Autowired
    UplataRepo uplataRepo;

    public Uplata getOne(Integer id){
        return uplataRepo.getOne(id);
    }

    public List<Uplata> getAll(){
        return uplataRepo.findAll();
    }

    public Uplata save(Uplata uplata){
        return uplataRepo.save(uplata);
    }

    public void remove(Integer id){
        uplataRepo.deleteById(id);
    }

    public List<Uplata> getByStudentJmbg(Long jmbg){return uplataRepo.findAllByStudent_Jmbg(jmbg);}

}
