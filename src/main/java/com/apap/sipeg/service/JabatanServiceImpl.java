package com.apap.sipeg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.sipeg.model.JabatanModel;
import com.apap.sipeg.repository.JabatanDB;

@Service 
@Transactional
public class JabatanServiceImpl implements JabatanService{
	@Autowired 
	JabatanDB jabatanDb;

	@Override
	public List<JabatanModel> getAll() {
		return jabatanDb.findAll();
	}

	@Override
	public JabatanModel getJabatanDetailById(long id) {
		return jabatanDb.findById(id);
	}

	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
	}

	@Override
	public void updateJabatan(JabatanModel oldJabatan, JabatanModel newJabatan) {	
		oldJabatan.setNama(newJabatan.getNama());
		oldJabatan.setDeskripsi(newJabatan.getDeskripsi());
		oldJabatan.setGaji_pokok(newJabatan.getGaji_pokok());
		jabatanDb.save(oldJabatan);
	}

	@Override
	public void deleteJabatan(long id) {
		jabatanDb.deleteById(id);
	}
}
