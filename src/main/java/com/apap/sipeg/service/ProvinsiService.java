package com.apap.sipeg.service;

import java.util.List;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.ProvinsiModel;

public interface ProvinsiService {
	List<ProvinsiModel> getAll();
	ProvinsiModel getProvinsiById(long id);
	List<InstansiModel> getAllInstansi(ProvinsiModel provinsi);
}
