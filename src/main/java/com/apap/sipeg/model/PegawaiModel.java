package com.apap.sipeg.model;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Syafiq Abdillah Umarghanis - 1606890574
 * PegawaiModel menggambarkan pegawai 
 *
 */
@Entity
@Table(name="Pegawai")
public class PegawaiModel implements Serializable{
	
	/*
	 * Id dari pegawai 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/*
	 * Nomor Induk Pegawai 
	 */
	@NotNull
	@Size(max = 255)
	@Column(name = "nip", nullable = false, unique = true)
	private String nip;
	
	/*
	 * nama pegawai 
	 */
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	/*
	 * tempat lahir pegawai 
	 */
	@NotNull
	@Size(max = 255)
	@Column(name = "tempat_lahir", nullable = false)
	private String tempat_lahir;
	
	/*
	 * tanggal lahir pegawai 
	 */
	@NotNull
	@Column(name = "tanggal_lahir", nullable = false)
	private Date tanggal_lahir;
	
	/*
	 * tahun masuk  pegawai 
	 */
	@NotNull
	@Size(max = 255)
	@Column(name = "tahun_masuk", nullable = false)
	private String tahun_masuk;
	
	/*
	 * id instansi tempat pegawai bekerja, Foreign Key ke INSTANSI.id 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_instansi", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private InstansiModel instansi;
	
	/*
	 * Hubungan many to many antara Jabatan dan Pegawai
	 * source : https://www.baeldung.com/hibernate-many-to-many
	 */
	 @ManyToMany(cascade = { CascadeType.ALL })
	    @JoinTable(
	        name = "Jabatan_Pegawai", 
	        joinColumns = { @JoinColumn(name = "id_pegawai") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_jabatan") }
	    )
	    List<JabatanModel> listJabatan = new ArrayList<>();

	public long getGaji() {
		Double tunjangan = this.instansi.getInstansiProvinsi().getPresentase_tunjangan();
		Double pokok = 0.0;
		//mencari gaji max
		for (JabatanModel jabatan : this.getListJabatan()) {
			if (jabatan.getGaji_pokok() > pokok) {
				pokok = jabatan.getGaji_pokok();
			}
		}
		Double gaji = (1.0 + tunjangan * 0.01) * pokok;
		long gajilong = gaji.intValue();
		return gajilong;
	}
	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNama() {
		return nama;
	}

	public List<JabatanModel> getListJabatan() {
		return listJabatan;
	}

	public void setListJabatan(List<JabatanModel> listJabatan) {
		this.listJabatan = listJabatan;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTempat_lahir() {
		return tempat_lahir;
	}

	public void setTempat_lahir(String tempat_lahir) {
		this.tempat_lahir = tempat_lahir;
	}

	public Date getTanggal_lahir() {
		return tanggal_lahir;
	}

	public void setTanggal_lahir(Date tanggal_lahir) {
		this.tanggal_lahir = tanggal_lahir;
	}

	public String getTahun_masuk() {
		return tahun_masuk;
	}

	public void setTahun_masuk(String tahun_masuk) {
		this.tahun_masuk = tahun_masuk;
	}

	public InstansiModel getInstansi() {
		return instansi;
	}

	public void setInstansi(InstansiModel instansi) {
		this.instansi = instansi;
	}

	
	
}
