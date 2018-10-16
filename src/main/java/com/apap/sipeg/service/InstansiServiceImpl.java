package com.apap.sipeg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.ProvinsiModel;
import com.apap.sipeg.repository.InstansiDB;

@Service 
@Transactional
public class InstansiServiceImpl implements InstansiService{
	@Autowired
	InstansiDB instansiDb;
		
	@Override
	public List<InstansiModel> getAll() {
		return instansiDb.findAll();
	}

	@Override
	public List<InstansiModel> getInstansiFromProvinsi(ProvinsiModel provinsi) {
		return provinsi.getProvinsiInstansi();
	}

	@Override
	public InstansiModel getInstansiDetailById(long id) {
		return instansiDb.findById(id);
	}

}
