package com.apap.sipeg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.ProvinsiModel;
import com.apap.sipeg.service.InstansiService;
import com.apap.sipeg.service.ProvinsiService;

@Controller
public class InstansiController {
	@Autowired 
	ProvinsiService provinsiService;
	
	@Autowired
	InstansiService instansiService;
	
	@RequestMapping(value = "/instansi/provinsi", method = RequestMethod.GET)
	public @ResponseBody
	List<InstansiModel> find(@RequestParam(value = "provinsiId", required = true) String provinsiId) {
		ProvinsiModel provinsi = provinsiService.getProvinsiById(Long.parseLong(provinsiId));
	    return instansiService.getInstansiFromProvinsi(provinsi);
	}
}
