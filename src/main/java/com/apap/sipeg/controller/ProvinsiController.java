package com.apap.sipeg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.ProvinsiModel;
import com.apap.sipeg.service.ProvinsiService;

public class ProvinsiController {
	@Autowired
	ProvinsiService provinsiService;
	
	@RequestMapping(value = "/provinsi/instansi", method = RequestMethod.GET)
	public @ResponseBody
	List<InstansiModel> findAllAgencies(@RequestParam(value = "provinsiId", required = true) Long provinsiId) {
	    ProvinsiModel provinsi = provinsiService.getProvinsiById(provinsiId);
	    return provinsiService.getAllInstansi(provinsi);
	}
	
}
