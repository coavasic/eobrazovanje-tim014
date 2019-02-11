package eobrazovanje.tim014.listener;

import eobrazovanje.tim014.model.*;
import eobrazovanje.tim014.repository.AdminRepo;
import eobrazovanje.tim014.repository.NastavnikRepo;
import eobrazovanje.tim014.repository.StudentRepo;
import eobrazovanje.tim014.repository.UplataRepo;
import eobrazovanje.tim014.service.FileStorageSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    NastavnikRepo nastavnikRepo;

    @Autowired
    FileStorageSevice fileStorageSevice;

    @Autowired
    UplataRepo uplataRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AdminRepo adminRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeStudent();
        initializeNastavnik();
        initialzeUplata();
        initializeAdmins();
        fileStorageSevice.deleteFiles();
    }

    public void initializeStudent(){
        Student student = new Student();
        student.setBrojIndeksa("SF15/2015");
        student.setBrojTelefona("062326386");
        student.setEmail("coavasic96@gmail.com");
        student.setKorisnickoIme("student1");
        student.setIme("Aleksandar");
        student.setPrezime("Vasic");
        student.setMesto("Valjevo");
        student.setLozinka(bCryptPasswordEncoder.encode("123"));
        student.setUlicaBroj("Valjevo");
        student.setPostanskiBroj(14221);
        studentRepo.save(student);

        Student student1 = new Student();
        student1.setBrojIndeksa("SF16/2015");
        student1.setBrojTelefona("062326386");
        student1.setEmail("coavasic96@gmail.com");
        student1.setKorisnickoIme("student2");
        student1.setIme("Dusan");
        student1.setPrezime("Vasic");
        student1.setMesto("Valjevo");
        student1.setLozinka(bCryptPasswordEncoder.encode("123"));
        student1.setUlicaBroj("Valjevo");
        student1.setPostanskiBroj(14221);
        studentRepo.save(student1);
    }

    public void initializeAdmins(){
        Administrator student = new Administrator();
        student.setBrojTelefona("062326386");
        student.setEmail("coavasic96@gmail.com");
        student.setKorisnickoIme("admin");
        student.setLozinka(bCryptPasswordEncoder.encode("admin"));
        student.setIme("Aleksandar");
        student.setPrezime("Vasic");
        student.setMesto("Valjevo");
        student.setUlicaBroj("Valjevo");
        student.setPostanskiBroj(14221);
        adminRepo.save(student);
    }

    public void initializeNastavnik(){
        Nastavnik student = new Nastavnik();
        student.setZvanje("Profesor");
        student.setBrojTelefona("062326386");
        student.setEmail("coavasic96@gmail.com");
        student.setKorisnickoIme("nastavnik1");
        student.setLozinka(bCryptPasswordEncoder.encode("123"));
        student.setIme("Aleksandar");
        student.setPrezime("Vasic");
        student.setMesto("Valjevo");
        student.setUlicaBroj("Valjevo");
        student.setPostanskiBroj(14221);
        nastavnikRepo.save(student);

        Nastavnik student1 = new Nastavnik();
        student1.setZvanje("Profesor");
        student1.setBrojTelefona("062326386");
        student1.setEmail("coavasic96@gmail.com");
        student1.setKorisnickoIme("nastavnik2");
        student1.setLozinka(bCryptPasswordEncoder.encode("123"));
        student1.setIme("Aleksandar");
        student1.setPrezime("Vasic");
        student1.setMesto("Valjevo");
        student1.setUlicaBroj("Valjevo");
        student1.setPostanskiBroj(14221);
        nastavnikRepo.save(student1);
    }

    public void initialzeUplata(){
        Uplata uplata = new Uplata();
        uplata.setDatumUplate(new Date());
        uplata.setIznos((float)13123.2);
        uplata.setSvrhaUplate("Prijava ispita");
        uplata.setStudent(studentRepo.findByBrojIndeksa("SF15/2015"));
        uplataRepo.save(uplata);
    }
}
