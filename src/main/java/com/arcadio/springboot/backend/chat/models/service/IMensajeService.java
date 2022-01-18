package com.arcadio.springboot.backend.chat.models.service;

import java.util.List;

import com.arcadio.springboot.backend.chat.models.documents.Mensaje;

public interface IMensajeService {

	public List<Mensaje> obtenerUltimosMensajes();
	public Mensaje guardar(Mensaje mensaje);
	
}
