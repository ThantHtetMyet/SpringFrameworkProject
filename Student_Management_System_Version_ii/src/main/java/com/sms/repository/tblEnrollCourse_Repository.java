package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.model.*;

@Repository
public interface tblEnrollCourse_Repository  extends JpaRepository<tblEnrollCourse,Integer>{

}
