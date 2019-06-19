package com.batchfour.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.batchfour.model.AppointmentArchive;

public interface AppointmentArchiveRepository extends CrudRepository<AppointmentArchive, Integer>{
    
    /**
     * This methods deletes all the records whose 'createdOn' date is less than 'expiryDate'
     */
    @Modifying 
    @Transactional // Make sure to import org.springframework.transaction.annotation.Transactional
    void deleteByCreatedOnBefore(Date expiryDate);

}
