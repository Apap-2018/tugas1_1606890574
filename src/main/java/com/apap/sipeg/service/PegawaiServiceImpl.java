package com.apap.sipeg.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.PegawaiModel;
import com.apap.sipeg.repository.PegawaiDB;

@Service 
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired 
	PegawaiDB pegawaiDb;
	
	@Override
	public PegawaiModel getPegawaiDetailByNIP(String nip) {
		return pegawaiDb.findByNip(nip);
	}

	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDb.save(pegawai);
	}

	@Override
	public List<PegawaiModel> getPegawaiInstansi(InstansiModel instansi) {
		return pegawaiDb.findByInstansiOrderByTanggalLahirAsc(instansi);
	}

	@Override
	public int getJmlPegawaiYangGini(InstansiModel instansi, Date tanggalLahir, String tahunMasuk) {
		return pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasuk(instansi, tanggalLahir, tahunMasuk).size();
	}

	@Override
	public void updatePegawai(PegawaiModel old, PegawaiModel newPegawai) {
		old.setNama(newPegawai.getNama());
		old.setTahunMasuk(newPegawai.getTahunMasuk());
		old.setTanggalLahir(newPegawai.getTanggalLahir());
		old.setTempatLahir(newPegawai.getTempatLahir());
		old.setInstansi(newPegawai.getInstansi());
		old.setListJabatan(newPegawai.getListJabatan());
		
		
		int pegawaiKe = getJmlPegawaiYangGini(newPegawai.getInstansi(), newPegawai.getTanggalLahir(), newPegawai.getTahunMasuk()) + 1;
		
		String kodeInstansi = Long.toString(newPegawai.getInstansi().getId());
		
		String pattern = "dd-MM-yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		String tanggalLahirString = simpleDateFormat.format(newPegawai.getTanggalLahir()).replaceAll("-", "");
		String pegawaiKeString = pegawaiKe/10 == 0 ? ("0" + Integer.toString(pegawaiKe)) : (Integer.toString(pegawaiKe));
		String nip = kodeInstansi + tanggalLahirString + newPegawai.getTahunMasuk() + pegawaiKeString;
		
		old.setNip(nip);
		
		pegawaiDb.save(old);
	}

}
