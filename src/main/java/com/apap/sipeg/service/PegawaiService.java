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
	void updatePegawai(PegawaiModel old, PegawaiModel newpegawai);
	List<PegawaiModel> getPegawaiInstansi(InstansiModel instansi);
	List<PegawaiModel> getPegawaiYangGini(InstansiModel instansi, Date tanggalLahir, String tahunMasuk);
}
