package com.ejemplo.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Habilita un broker simple para manejar mensajes entrantes y salientes
        config.enableSimpleBroker("/topic"); // Los mensajes publicados serán enviados a "/topic".
        config.setApplicationDestinationPrefixes("/app"); // Prefijo para mensajes entrantes desde clientes.
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Define un endpoint para establecer las conexiones WebSocket
        registry.addEndpoint("/chat").withSockJS(); // Agrega soporte para SockJS (fallback si WebSocket no está disponible).
    }
}