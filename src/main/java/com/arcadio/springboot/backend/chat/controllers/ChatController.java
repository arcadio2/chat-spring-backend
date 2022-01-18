package com.arcadio.springboot.backend.chat.controllers;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.arcadio.springboot.backend.chat.models.documents.Mensaje;

@Controller
public class ChatController {

	@MessageMapping("/mensaje")//app/mensaje en realidad
	public Mensaje recibeMensaje(Mensaje mensaje) {//recibe info
		mensaje.setFecha(new Date().getTime());
		mensaje.setTexto("Recibido por el broker "+mensaje.getTexto());
		return mensaje; 
	}
	
}
