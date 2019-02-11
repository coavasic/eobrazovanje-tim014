package eobrazovanje.tim014.service;

import eobrazovanje.tim014.model.Predmet;
import eobrazovanje.tim014.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PredmetService {

    @Autowired
    ObavezaRepo obavezaRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    PohadjaRepo pohadjaRepo;

    @Autowired
    PredmetRepo predmetRepo;

    @Autowired
    PredajeRepo predajeRepo;

    public List<Predmet> sviPredmetiKojeStudentPohadja(String brojIndeksa){
        List<Predmet> predmeti  = pohadjaRepo.findAllByStudent_BrojIndeksa(brojIndeksa).stream().map(
                pohadja -> {Predmet predmet = predmetRepo.getOne(pohadja.getPredmet().getPredmetId());
                    return predmet;}
        ).collect(Collectors.toList());

        return predmeti;
    }

    public List<Predmet> sviPredmetiKojeNastavnikPredaje(Long jmbg){
        List<Predmet> predmeti  = predajeRepo.findAllByNastavnik_Jmbg(jmbg).stream().map(
                pohadja -> {Predmet predmet = predmetRepo.getOne(pohadja.getPredmet().getPredmetId());
                    return predmet;}
        ).collect(Collectors.toList());

        return predmeti;
    }

    public boolean pohadjaLiPredmet(Integer id,String brojIndeksa){

        for(Predmet  predmet:sviPredmetiKojeStudentPohadja(brojIndeksa)){
            if(predmet.getPredmetId() == id){
                return true;
            }
        }

        return false;
    }

    public boolean predajeLiPredmet(Integer id,Long jmbg){
        for(Predmet predmet:sviPredmetiKojeNastavnikPredaje(jmbg)){
            if(predmet.getPredmetId() == id){
                return true;
            }
        }

        return false;

    }

    public Predmet getOne(Integer id){return predmetRepo.getOne(id);}
    public List<Predmet> getAll(){return predmetRepo.findAll();}
}
