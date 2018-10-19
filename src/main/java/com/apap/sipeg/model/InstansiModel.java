package com.apap.sipeg.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Syafiq Abdillah Umarghanis - 1606890574
 * InstansiModel menggambarkan instansi 
 *
 */
@Entity
@Table(name="Instansi")
public class InstansiModel implements Serializable{

	/*
	 * Id dari instansi 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/*
	 * nama instansi 
	 */
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	/*
	 * deskripsi instansi 
	 */
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;
	
	/*
	 * instansi berada di satu provinsi 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_provinsi", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private ProvinsiModel instansiProvinsi;
	
	/*
	 * instansi memiliki beberapa pegawai 
	 */
	@OneToMany(mappedBy = "instansi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PegawaiModel> listPegawai;

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

	public ProvinsiModel getInstansiProvinsi() {
		return instansiProvinsi;
	}

	public void setInstansiProvinsi(ProvinsiModel instansiProvinsi) {
		this.instansiProvinsi = instansiProvinsi;
	}

	public List<PegawaiModel> getInstansiPegawai() {
		return listPegawai;
	}

	public void setInstansiPegawai(List<PegawaiModel> instansiPegawai) {
		this.listPegawai = instansiPegawai;
	}
	
	
}
