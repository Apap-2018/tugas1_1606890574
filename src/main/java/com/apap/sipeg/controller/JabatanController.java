package com.apap.sipeg.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.sipeg.model.JabatanModel;
import com.apap.sipeg.service.JabatanService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
public class JabatanController {
	@Autowired 
	JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String view(@RequestParam(value = "idJabatan") String idJabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(Long.parseLong(idJabatan));
		model.addAttribute("jabatan", jabatan);
		return "view-jabatan";
	}
	
	@RequestMapping("/jabatan/viewall")
	private String viewallJabatan(Model model) {
		model.addAttribute("listJabatan", jabatanService.getAll());
		return "viewall-jabatan";
	}
	
	@RequestMapping("/jabatan/viewall-jml-pegawai")
	private String viewallJumlahPegawai(Model model) {
		model.addAttribute("listJabatan", jabatanService.getAll());
		return "viewall-jabatan-jml-pegawai";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String addJabatan(Model model) {
		JabatanModel jabatan = new JabatanModel();
		model.addAttribute("jabatan", jabatan);
		return "add-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(Model model, @ModelAttribute JabatanModel jabatan) {
		jabatanService.addJabatan(jabatan);
		return "add-jabatan-success";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String updateJabatan(@RequestParam(value = "idJabatan") String idJabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(Long.parseLong(idJabatan));
		model.addAttribute("jabatan", jabatan);
		return "update-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String updateJabatanSubmit(@RequestParam(value = "idJabatan") String idJabatan, Model model, @ModelAttribute JabatanModel jabatan) {
		JabatanModel old = jabatanService.getJabatanDetailById(Long.parseLong(idJabatan));
		jabatanService.updateJabatan(old, jabatan);
		return "update-jabatan-success";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	private String delete(@RequestParam(value = "idJabatan") String idJabatan, Model model) {
		try {
			jabatanService.deleteJabatan(Long.parseLong(idJabatan));
			return "delete-jabatan-success";
		} catch (Exception e) {
			return "delete-jabatan-fail";
		}
		
		
	}
}
