package com.apap.sipeg.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.sipeg.model.PegawaiModel;
@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel, Long>{
	 PegawaiModel findByNip(String nip);
}
