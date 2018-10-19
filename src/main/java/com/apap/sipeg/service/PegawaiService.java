package com.apap.sipeg.service;

import java.util.Date;
import java.util.List;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.PegawaiModel;

/*
 * Pegawai Service 
 */
public interface PegawaiService {
	PegawaiModel getPegawaiDetailByNIP(String nip);
	void addPegawai(PegawaiModel pegawai);
	List<PegawaiModel> getPegawaiInstansi(InstansiModel instansi);
	int getJmlPegawaiYangGini(InstansiModel instansi, Date tanggalLahir, String tahunMasuk);
}
