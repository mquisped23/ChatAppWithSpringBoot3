package com.ejemplo.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@Controller
public class ChatController {

    @GetMapping("/")
    public String index() {
        return "index"; // Carga la página principal
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Agregar el username al modelo
        model.addAttribute("username", username);

        return "chat";
    }

    // Metodo para WebSockets ya existente:
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String sendMessage(String message) {
        return message;
    }
}
