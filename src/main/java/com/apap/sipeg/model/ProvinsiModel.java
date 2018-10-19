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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apap.sipeg.model.InstansiModel;
import com.fasterxml.jackson.annotation.JsonIgnore; 


/**
 * 
 * @author Syafiq Abdillah Umarghanis - 1606890574
 * ProvinsiModel menggambarkan Provinsi 
 *
 */
@Entity
@Table(name="Provinsi")
public class ProvinsiModel implements Serializable{
	/*
	 * Id dari provinsi 
	 */
	@Id
	@Size (max = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/*
	 * nama provinsi 
	 */
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	/*
	 * presentase tunjangan di provinsi
	 */
	@NotNull
	@Column(name = "presentase_tunjangan", nullable = false)
	private Double presentase_tunjangan;
	
	/*
	 * provinsi memiliki beberapa instansi 
	 */
	@OneToMany(mappedBy = "instansiProvinsi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<InstansiModel> listInstansi;

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

	public Double getPresentase_tunjangan() {
		return presentase_tunjangan;
	}

	public void setPresentase_tunjangan(Double presentase_tunjangan) {
		this.presentase_tunjangan = presentase_tunjangan;
	}

	public List<InstansiModel> getProvinsiInstansi() {
		return listInstansi;
	}

	public void setProvinsiInstansi(List<InstansiModel> provinsiInstansi) {
		this.listInstansi = provinsiInstansi;
	}
	
}
