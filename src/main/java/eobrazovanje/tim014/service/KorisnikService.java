package eobrazovanje.tim014.service;

import eobrazovanje.tim014.model.Korisnik;
import eobrazovanje.tim014.repository.KorisnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    KorisnikRepo korisnikRepo;

    public List<Korisnik> getAll(){return korisnikRepo.findAll();}

}
