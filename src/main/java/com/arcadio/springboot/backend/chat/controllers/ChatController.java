package com.arcadio.springboot.backend.chat.controllers;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.arcadio.springboot.backend.chat.models.documents.Mensaje;
import com.arcadio.springboot.backend.chat.models.service.MensajeServiceImpl;

@Controller
@CrossOrigin({"http://localhost:4200"})
public class ChatController {
	
	@Autowired
	private MensajeServiceImpl chatService; 
	
	@Autowired
	private SimpMessagingTemplate webSocket;//hace lo de sendTo
	
	private String[] colores = {"red","green","blue","orange","purple","magenta"};
	
	
	@MessageMapping("/mensaje")//app/mensaje en realidad
	@SendTo("/chat/mensaje")//evento mensaje
	public Mensaje recibeMensaje(Mensaje mensaje) {//recibe info
		
		mensaje.setFecha(new Date().getTime());
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("Nuevo usuario ");
		}else {
			chatService.guardar(mensaje);
		}
		//mensaje.setTexto("Recibido por el broker "+mensaje.getTexto());

		return mensaje; 
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) { 
		if(!username.isEmpty()) {
			return username+" Está escribiendo...";
		}else {
			return "";
		}
		
	}
	
	@MessageMapping("/historial")
	//@SendTo("/chat/historial")
	public void ultimos10(String clienteId){
		System.out.println("xd");
		webSocket.convertAndSend("/chat/historial/"+clienteId,chatService.obtenerUltimosMensajes());
		//return chatService.obtenerUltimosMensajes();
	}
	
}
