package com.sms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sms.model.*;

@Repository
public interface tblFaculty_Repository extends JpaRepository<tblFaculty,Integer>{
    
}
