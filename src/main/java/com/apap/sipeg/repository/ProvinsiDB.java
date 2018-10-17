package com.apap.sipeg.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.sipeg.model.ProvinsiModel;

@Repository
public interface ProvinsiDB extends JpaRepository<ProvinsiModel, Long>{
	ProvinsiModel findById(long id);
}
