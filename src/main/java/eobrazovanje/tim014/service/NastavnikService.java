package eobrazovanje.tim014.service;

import eobrazovanje.tim014.model.Nastavnik;
import eobrazovanje.tim014.model.Predaje;
import eobrazovanje.tim014.model.Predmet;
import eobrazovanje.tim014.model.Student;
import eobrazovanje.tim014.repository.NastavnikRepo;
import eobrazovanje.tim014.repository.PredajeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NastavnikService {

    @Autowired
    NastavnikRepo nastavnikRepo;

    @Autowired
    PredajeRepo predajeRepo;

    public Nastavnik getOne(long id){return nastavnikRepo.getOne(id);}
    public List<Nastavnik> getAll(){return  nastavnikRepo.findAll();}
    public Nastavnik add(Nastavnik nastavnik) {return nastavnikRepo.save(nastavnik);}
    public void delete(Long jmbg){nastavnikRepo.deleteByJmbg(jmbg);}

    public boolean predajeLiPredmet(Integer id,Long jmbg){
        List<Predaje> predmeti = predajeRepo.findAllByNastavnik_Jmbg(jmbg);
        for (Predaje predaje:predmeti) {
            if(predaje.getPredmet().getPredmetId() == id){
                return true;
            }
        }

        return false;
    }

    public List<Nastavnik> findByPredmetKojiPredaje(Integer predmetId){
        List<Nastavnik> nastavnici = predajeRepo.findAllByPredmet_PredmetId(predmetId).stream().map(
                pohadja -> {
                    return nastavnikRepo.getOne(pohadja.getNastavnik().getJmbg());
                }
        ).collect(Collectors.toList());

        return nastavnici;
    }

    public List<Nastavnik> nastavnikKojiNePredajePredmet(Integer predmetId){
        List<Nastavnik> nastavnici = nastavnikRepo.findAll().stream().filter(
                student -> !findByPredmetKojiPredaje(predmetId).contains(student)
        ).collect(Collectors.toList());

        return nastavnici;
    }
}
