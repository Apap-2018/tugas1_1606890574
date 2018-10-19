package com.apap.sipeg.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.PegawaiModel;
@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel, Long>{
	 PegawaiModel findByNip(String nip);
	 List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansi);
	 List<PegawaiModel> findByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggal_lahir, String tahun_masuk);
}
