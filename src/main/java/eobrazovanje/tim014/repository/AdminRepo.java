package eobrazovanje.tim014.repository;

import eobrazovanje.tim014.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AdminRepo extends  JpaRepository<Administrator,Long>{

}