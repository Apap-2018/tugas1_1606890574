package com.apap.sipeg.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.sipeg.model.JabatanModel;

@Repository
public interface JabatanDB extends JpaRepository<JabatanModel, Long>{
	JabatanModel findById(long id);
}
