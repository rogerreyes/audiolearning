package com.ud.audiolearning.CargaDescarga.service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSFile;
import com.ud.audiolearning.CargaDescarga.dao.CargaDescargaDao;
import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.dao.IAudioDao;
import com.ud.audiolearning.api.domain.Audio;

@AudioLService
public class CargaDescargaService {

	@Autowired
	IAudioDao audioDao;
	@Autowired
	CargaDescargaDao cargaDescargaDao;

	public boolean guardarAudio(Audio audio) {
		try {
			audioDao.salvarAudio(audio);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public GridFSFile salvarAudioFile(ByteArrayOutputStream baos, String contentType, DBObject metaData) {

		return cargaDescargaDao.saveAudioFile(baos.toByteArray(), contentType, metaData);
	}

	public GridFSFile salvarAudioFile2(ByteArrayOutputStream baos, Audio nuevoAudio) {
		InputStream inputStream = null;
		try {
			inputStream = new ByteArrayInputStream(baos.toByteArray());
			return cargaDescargaDao.saveAudioFile2(inputStream, nuevoAudio.getFileName(), nuevoAudio.getFileMimeType());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
}
