package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DokumentRepo  extends JpaRepository<Dokument,Integer> {
}
