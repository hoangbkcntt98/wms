package com.wms.demo.repository;

import com.wms.demo.model.AnnualDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnualDemandRepository extends JpaRepository<AnnualDemand,Integer> {

    @Query("select a FROM AnnualDemand a ORDER BY a.inventory.price*a.annualDemand  DESC")
    List<AnnualDemand> findAll();
}
