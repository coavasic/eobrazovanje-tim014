package eobrazovanje.tim014.listener;

import eobrazovanje.tim014.model.Student;
import eobrazovanje.tim014.repository.StudentRepo;
import eobrazovanje.tim014.service.FileStorageSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    FileStorageSevice fileStorageSevice;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeStudent();
        fileStorageSevice.deleteFiles();
    }

    public void initializeStudent(){
        Student student = new Student();
        student.setBrojIndeksa("SF15/2015");
        student.setBrojTelefona("062326386");
        student.setEmail("coavasic96@gmail.com");
        student.setKorisnickoIme("coavasic96");
        student.setIme("Aleksandar");
        student.setPrezime("Vasic");
        student.setMesto("Valjevo");
        student.setUlicaBroj("Valjevo");
        student.setPostanskiBroj(14221);
        studentRepo.save(student);
    }
}
