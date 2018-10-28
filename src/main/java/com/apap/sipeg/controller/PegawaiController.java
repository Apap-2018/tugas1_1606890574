package com.apap.sipeg.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
	
	/*
	 * collaborator : github.com/zakiraihan
	 * dengan penyesuaian 
	 */
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"addJabatan"})
	private String addRow(@ModelAttribute PegawaiModel pegawai, Model model) {
		System.out.println("masuk add jabatan");
		System.out.println(pegawai.getListJabatan().size());
		pegawai.getListJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		/*
		 * penyesuaian
		 */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getInstansiProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		return "add-pegawai";
	}
	
	/*
	 * collaborator : github.com/zakiraihan
	 * dengan penyesuaian 
	 */
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"deleteJabatan"})
	private String deleteRow(@ModelAttribute PegawaiModel pegawai, Model model, HttpServletRequest req) {
		
		Integer rowId =  Integer.valueOf(req.getParameter("deleteJabatan"));
		pegawai.getListJabatan().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai); 
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getInstansiProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		return "add-pegawai";
	}
	/*
	 * collaborator : github.com/zakiraihan
	 * dengan penyesuaian 
	 */
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	public String tambahPegawai (@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawai.setNip(nipGenerator(pegawai));
		
		pegawaiService.addPegawai(pegawai);
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("nip", pegawai.getNip());
		
		return "add-pegawai-success";
	}
	
	public String nipGenerator(PegawaiModel pegawai) {
		InstansiModel instansi = pegawai.getInstansi();
		Date tanggalLahir = pegawai.getTanggalLahir();
		String tahunMasuk = pegawai.getTahunMasuk();
		
		String duaTerakhir = get2digitTerakhir(tanggalLahir, instansi, tahunMasuk);
		
		//4 digit awal adalah kode instansi
		String kodeInstansi = Long.toString(instansi.getId());
 
		
		String pattern = "dd-MM-yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		//6 digit selanjutnya adalah tanggal lahir
		String tanggalLahirString = simpleDateFormat.format(tanggalLahir).replaceAll("-", "");
		
		String nip = kodeInstansi + tanggalLahirString + tahunMasuk + duaTerakhir;
		
		
		return nip;
	}
	
	public String get2digitTerakhir(Date tanggalLahir, InstansiModel instansi, String tahunMasuk) {
		
		//mencari pegawai dengan instansi, tanggal lahir, dan tahun masuk seperti param 
		List<PegawaiModel> listPegawai = pegawaiService.getPegawaiYangGini(instansi, tanggalLahir, tahunMasuk);
		//kalau tidak ada, 2 nomor terakhir adalah 01 
		if (listPegawai.isEmpty()) {
			return "01";
		} else {
			System.out.println("masuk ");
			//membuat array integer berisi masing masing 2 nomor terakhir nip 
			ArrayList<Integer> list2Last = new ArrayList<Integer>();
			//mengisi array 
			for (PegawaiModel pegawai : listPegawai) {
				
				String nip = pegawai.getNip().substring(14, 16);
				int nipInt = Integer.parseInt(nip);
				list2Last.add(nipInt);
				System.out.println("mengisi " + nipInt + " ke array");
			}
			
			//array telah terisi dengan 2 digit terakhir 
			
			//mengurutkan list tersebut, datum di urutan paling akhir adalah yang pertama
			Collections.sort(list2Last);
			
			//menambahkan nomor terakhir dengan 1 
			int calon2terakhir = list2Last.get(list2Last.size() - 1) + 1;
			
			System.out.println("calon " + calon2terakhir);
			
			String result = Integer.toString(calon2terakhir);
			
			//jika nomor terakhir kurang dari 10, maka ditambahkan 0 depannya 
			if (calon2terakhir < 10) {
				result = "0" + result;
			}
			
			//testing
			for (PegawaiModel pegawaaaai : listPegawai) {
				System.out.println(pegawaaaai.getNip() + pegawaaaai.getNama());
			}
			
			return result;
		}
	}
	
//	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
//	public String tambahPegawai (@ModelAttribute PegawaiModel pegawai, Model model) {
//		
//		InstansiModel instansi = pegawai.getInstansi();
//		Date tanggalLahir = pegawai.getTanggalLahir();
//		String tahunMasuk = pegawai.getTahunMasuk();
//		int pegawaiKe = pegawaiService.getJmlPegawaiYangGini(instansi, tanggalLahir, tahunMasuk) + 1;
//		
//		String kodeInstansi = Long.toString(instansi.getId());
//		
//		String pattern = "dd-MM-yy";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		
//		String tanggalLahirString = simpleDateFormat.format(tanggalLahir).replaceAll("-", "");
//		String pegawaiKeString = pegawaiKe/10 == 0 ? ("0" + Integer.toString(pegawaiKe)) : (Integer.toString(pegawaiKe));
//		String nip = kodeInstansi + tanggalLahirString + tahunMasuk + pegawaiKeString;
//		
//		pegawai.setNip(nip);
//		
//		pegawaiService.addPegawai(pegawai);
//		
//		System.out.println("nip : " + nip);
//		model.addAttribute("pegawai", pegawai);
//
//		return "add-pegawai-success";
//	}
	
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

