package com.wms.demo.repository;

import com.wms.demo.model.Abc;
import com.wms.demo.model.AnnualDemand;
import com.wms.demo.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbcRepository extends JpaRepository<Abc,Integer> {
    @Query("select a FROM Abc a ORDER BY a.range DESC")
    List<Abc> findAll();
}
