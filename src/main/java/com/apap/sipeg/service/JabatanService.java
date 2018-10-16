package com.apap.sipeg.service;
import java.util.List;

import com.apap.sipeg.model.JabatanModel;
/*
 * Jabatan Service
 */
public interface JabatanService {
	List<JabatanModel> getAll();
	JabatanModel getJabatanDetailById(long id);
	void addJabatan(JabatanModel jabatan);
	void updateJabatan(JabatanModel oldJabatan, JabatanModel newJabatan);
	void deleteJabatan(long id);
}
