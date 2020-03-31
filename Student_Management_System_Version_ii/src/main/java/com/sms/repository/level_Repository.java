package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sms.model.level;

@Repository
public interface level_Repository extends JpaRepository<level,Integer>
{

}
