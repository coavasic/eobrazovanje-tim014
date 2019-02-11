package eobrazovanje.tim014.service;

import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.model.Obaveza;
import eobrazovanje.tim014.model.Predaje;
import eobrazovanje.tim014.model.Predmet;
import eobrazovanje.tim014.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ObavezaService {

    @Autowired
    ObavezaRepo obavezaRepo;

    @Autowired
    PredajeRepo predajeRepo;

    @Autowired
    NastavnikService nastavnikService;

    @Autowired
    KorisnikRepo korisnikRepo;


    public List<Obaveza> getAll(){return obavezaRepo.findAll();}
    public Obaveza getOnt(Integer id){return obavezaRepo.getOne(id);}
    public List<Obaveza> getAllByNastavnik(Long jmbg){
        List<Obaveza> obaveze = obavezaRepo.findAll().stream().filter(
                obaveza -> {
                    if(nastavnikService.predajeLiPredmet(obaveza.getPredmet().getPredmetId(),jmbg)){
                        return true;
                    }

                    return false;
                }

        ).collect(Collectors.toList());

        return obaveze;
             }

    public Obaveza save(Obaveza obaveza){return obavezaRepo.save(obaveza);}





}