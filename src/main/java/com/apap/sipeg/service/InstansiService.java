package com.apap.sipeg.service;

import java.util.List;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.ProvinsiModel;

public interface InstansiService {
	List<InstansiModel> getAll();
	List<InstansiModel> getInstansiFromProvinsi(ProvinsiModel provinsi);
	InstansiModel getInstansiDetailById(long id);
	
}
