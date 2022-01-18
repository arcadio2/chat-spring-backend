package com.arcadio.springboot.backend.chat.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcadio.springboot.backend.chat.models.dao.ChatDao;
import com.arcadio.springboot.backend.chat.models.documents.Mensaje;

@Service
public class MensajeServiceImpl implements IMensajeService{

	@Autowired 
	private ChatDao chatDao;

	@Override
	public List<Mensaje> obtenerUltimosMensajes() {
	
		return chatDao.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {

		return chatDao.save(mensaje);
	}
	
	
	
	
}
