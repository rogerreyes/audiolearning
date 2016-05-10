package com.ud.audiolearning.administracion.service;

import java.util.HashMap;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.ud.audiolearning.administracion.dao.AdministracionDao;
import com.ud.audiolearning.api.anotaciones.AudioLService;
import com.ud.audiolearning.api.dao.IAudioDao;
import com.ud.audiolearning.api.domain.Audio;
import com.ud.audiolearning.api.domain.Denuncia;
import com.ud.audiolearning.api.domain.Rol;
import com.ud.audiolearning.api.domain.Usuario;


@AudioLService
public class AdministracionService {

	@Autowired
	AdministracionDao administracionDao;
	
	@Autowired
	IAudioDao audioDao;

	public List<Denuncia> denunciasPorAtender() {
		return administracionDao.denunciasSinAtender();
	}
	
	public List<Denuncia> denunciasProcesadas() {
		return administracionDao.denunciasProcesadas();
	}

	public void guardarDenuncia(Denuncia denuncia) {
		administracionDao.insertarDenuncia(denuncia);
	}
	
	public Denuncia consultarDetalleDenuncia(ObjectId idDenuncia){
		return administracionDao.buscarDenuncia(idDenuncia);
	} 
	
	public Audio consultarDatosAudio(String idAudio){
		return audioDao.findOneAudio(idAudio);
	}
	
	public void procesarDenuncia(Denuncia denuncia){
		administracionDao.actualizarDenunciaCierre(denuncia);
	}
	
	public Integer crearUsuario(Usuario usuario){
		try {
			administracionDao.insertarUsuario(usuario);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	public Integer modificarUsuario(Usuario usuario){
		try {			
			administracionDao.modificarUsuario(usuario);
		} catch (Exception e) {
			return 0;
		} 
		return 1;
	}
	
	public Integer eliminarUsuario(Usuario usuario){
		try {
			administracionDao.eliminarUsuario(usuario);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	public List<Usuario> listadoUsuarios(HashMap<String,String> Parametros){
		
		System.err.println(Parametros);
		return administracionDao.listadoUsuarios(Parametros);
	}
	
	public List<Rol> listadoRoles(){
		return administracionDao.listadoRol();
	}
	
	public Rol consultarRol (String id){
		return administracionDao.consultaROL(id);
	}
}
