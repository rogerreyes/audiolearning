package com.ud.audiolearning.reproductor.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import com.mongodb.gridfs.GridFSDBFile;
import com.ud.audiolearning.api.anotaciones.AudioLDao;

@AudioLDao
public class ReproductorDao {

	@Autowired
	@Qualifier("audioGFS")
	GridFsTemplate gt;

	public GridFSDBFile findAudioFile(String id) {
		return gt.findOne(new Query().addCriteria(Criteria.where("_id").is(id)));
	}

}
