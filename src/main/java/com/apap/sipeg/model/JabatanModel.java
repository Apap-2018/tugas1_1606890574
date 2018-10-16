package com.apap.sipeg.model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 
 * @author Syafiq Abdillah Umarghanis - 1606890574
 * JabatanModel menggambarkan Jabatan 
 *
 */
@Entity
@Table(name="Jabatan")
public class JabatanModel implements Serializable{
	/*
	 * Id dari jabatan  
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/*
	 * nama jabatan 
	 */
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	/*
	 * deskripsi jabatan 
	 */
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;
	
	/*
	 * gaji pokok dari jabatan 
	 */
	@NotNull
	@Column(name = "gaji_pokok", nullable = false)
	private Double gaji_pokok;
	
	/*
	 * hubungan many to mant dengan pegawai
	 */
	@ManyToMany(mappedBy = "listJabatan")
    private Set<PegawaiModel> listPegawai = new HashSet<>();

	public Set<PegawaiModel> getListPegawai() {
		return listPegawai;
	}

	public void setListPegawai(Set<PegawaiModel> listPegawai) {
		this.listPegawai = listPegawai;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Double getGaji_pokok() {
		return gaji_pokok;
	}

	public void setGaji_pokok(Double gaji_pokok) {
		this.gaji_pokok = gaji_pokok;
	}
	
	
}
