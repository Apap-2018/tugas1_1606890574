package com.apap.sipeg.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.JabatanModel;
import com.apap.sipeg.model.PegawaiModel;
import com.apap.sipeg.model.ProvinsiModel;
import com.apap.sipeg.service.InstansiService;
import com.apap.sipeg.service.JabatanService;
import com.apap.sipeg.service.PegawaiService;
import com.apap.sipeg.service.ProvinsiService;


@Controller
public class PegawaiController {
	@Autowired
	PegawaiService pegawaiService;
	
	@Autowired
	JabatanService jabatanService;
	
	@Autowired
	InstansiService instansiService;
	
	@Autowired
	ProvinsiService provinsiService;
	
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("listJabatan", jabatanService.getAll());
		model.addAttribute("listInstansi", instansiService.getAll());
		return "home";
	}
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String view(@RequestParam(value = "nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNIP(nip);
		String instansi = pegawai.getInstansi().getNama();
		String provinsi = pegawai.getInstansi().getInstansiProvinsi().getNama();
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("nama_instansi", instansi);
		model.addAttribute("nama_provinsi", provinsi);
		model.addAttribute("gaji", pegawai.getGaji());
		
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai( Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setListJabatan(new ArrayList<JabatanModel>());
		pegawai.getListJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		List<InstansiModel> listInstansi = instansiService.getAll();
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		model.addAttribute("tanggallahir", "");
		
		return "add-pegawai";
	}
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	public String tambahPegawai (@ModelAttribute PegawaiModel pegawai, Model model) {
		System.out.println("1 masuk sini, " + pegawai.getNama());
		System.out.println("2 tahun masuk, " + pegawai.getTahunMasuk());
		System.out.println("3 tempat lahir, " + pegawai.getTempatLahir());
		System.out.println("3 tanggal lahir, " + pegawai.getTanggalLahir());
		System.out.println("4 nama instansi, " + pegawai.getInstansi().getNama());
		System.out.println("5 nama jabatan, " + pegawai.getListJabatan().get(0).getNama());
		
		InstansiModel instansi = pegawai.getInstansi();
		Date tanggalLahir = pegawai.getTanggalLahir();
		String tahunMasuk = pegawai.getTahunMasuk();
		int pegawaiKe = pegawaiService.getJmlPegawaiYangGini(instansi, tanggalLahir, tahunMasuk) + 1;
		
		String kodeInstansi = Long.toString(instansi.getId());
		
		String pattern = "dd-MM-yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		String tanggalLahirString = simpleDateFormat.format(tanggalLahir).replaceAll("-", "");
		String pegawaiKeString = pegawaiKe/10 == 0 ? ("0" + Integer.toString(pegawaiKe)) : (Integer.toString(pegawaiKe));
		String nip = kodeInstansi + tanggalLahirString + tahunMasuk + pegawaiKeString;
		
		pegawai.setNip(nip);
		
		pegawaiService.addPegawai(pegawai);
		
		System.out.println("nip : " + nip);
		model.addAttribute("pegawai", pegawai);

		return "add-pegawai-success";
	}
	
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	private String cari( Model model) {
		model.addAttribute("provinsi",provinsiService.getAll());
		model.addAttribute("listInstansi", instansiService.getAll());
		model.addAttribute("listJabatan", jabatanService.getAll());
		return "cari-pegawai";
	}

	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	private String updatePegawai(@RequestParam(value = "nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNIP(nip);
		model.addAttribute("pegawai", pegawai);
		
		List<InstansiModel> listInstansi = instansiService.getAll();
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		model.addAttribute("tanggallahir", "");
		model.addAttribute("nip", nip);
		
		return "update-pegawai";
	}
	

	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String updatePegawaiSubmit(@RequestParam(value = "nip") String nip, Model model, @ModelAttribute PegawaiModel pegawai) {
		PegawaiModel old = pegawaiService.getPegawaiDetailByNIP(nip);
		pegawaiService.updatePegawai(old, pegawai);
		model.addAttribute("niplama", nip);
		return "update-pegawai-success";
	}
	
	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String tuamuda(@RequestParam(value = "idInstansi") String idInstansi, Model model) {
		InstansiModel instansi = instansiService.getInstansiDetailById(Long.parseLong(idInstansi));
		PegawaiModel tua = pegawaiService.getPegawaiInstansi(instansi).get(0);
		PegawaiModel muda = pegawaiService.getPegawaiInstansi(instansi).get(pegawaiService.getPegawaiInstansi(instansi).size() - 1);
		//sampai sini sudah terlihat pegawai tertua dan termuda
		model.addAttribute("instansi", instansi);
		model.addAttribute("tua", tua);
		model.addAttribute("muda", muda);
		return "termuda-tertua";
	}
	
}

