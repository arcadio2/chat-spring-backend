package com.arcadio.springboot.backend.chat.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.arcadio.springboot.backend.chat.models.documents.Mensaje;

public interface ChatDao extends MongoRepository<Mensaje,String>{

	public List<Mensaje> findFirst10ByOrderByFechaDesc();
	
} 
 