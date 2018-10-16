package com.apap.sipeg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.sipeg.model.PegawaiModel;
import com.apap.sipeg.repository.PegawaiDB;

@Service 
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired 
	PegawaiDB pegawaiDb;
	
	@Override
	public PegawaiModel getPegawaiDetailByNIP(String nip) {
		return pegawaiDb.findByNip(nip);
	}

	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDb.save(pegawai);
	}

}
