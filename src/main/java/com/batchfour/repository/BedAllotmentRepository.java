package com.batchfour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.batchfour.model.BedAllotment;

public interface BedAllotmentRepository extends JpaRepository<BedAllotment, Integer>{
//    @Query("select u from BedAllotment u where u.status  =?1")
    List<BedAllotment> findByBedAllotmentStatus(String status);

//  @Query("select u from BedAllotment u where u.status  =?1")
    public int findByBedAllotmentID(int bedAllotmentID);
}
