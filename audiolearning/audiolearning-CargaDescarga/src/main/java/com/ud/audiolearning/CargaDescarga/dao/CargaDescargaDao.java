package com.ud.audiolearning.CargaDescarga.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSFile;
import com.ud.audiolearning.api.anotaciones.AudioLDao;

@AudioLDao
public class CargaDescargaDao {

	@Autowired
	@Qualifier("audioGFS")
	GridFsTemplate gt;

	public GridFSFile saveAudioFile(byte[] bs, String contentType, DBObject metaData) {

		InputStream inputStream = null;
		try {
			inputStream = new ByteArrayInputStream(bs);
			GridFSFile archivo = gt.store(inputStream, "a", contentType, metaData);
			return archivo;

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

	public GridFSFile saveAudioFile2(InputStream inputStream, String fileName, String contentType) {
		return gt.store(inputStream, fileName, contentType);
	}

}
