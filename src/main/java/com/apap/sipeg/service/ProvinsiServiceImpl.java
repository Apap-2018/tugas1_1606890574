package com.apap.sipeg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.ProvinsiModel;
import com.apap.sipeg.repository.ProvinsiDB;

@Service 
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService{
	@Autowired 
	ProvinsiDB provinsiDb;

	@Override
	public List<ProvinsiModel> getAll() {
		return provinsiDb.findAll();
	}

	@Override
	public ProvinsiModel getProvinsiById(long id) {
		return provinsiDb.findById(id);
	}

	@Override
	public List<InstansiModel> getAllInstansi(ProvinsiModel provinsi) {
		return provinsi.getProvinsiInstansi();
	}
}
