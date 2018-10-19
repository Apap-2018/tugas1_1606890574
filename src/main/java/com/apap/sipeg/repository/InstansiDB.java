package com.apap.sipeg.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.ProvinsiModel;

@Repository
public interface InstansiDB extends JpaRepository<InstansiModel, Long>{
	InstansiModel findById(long id);
	List<InstansiModel> findByInstansiProvinsi(ProvinsiModel provinsi);
}
