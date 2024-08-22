package com.example.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

@SpringBootApplication
public class SocketApplication {
	
	@Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9090);
        return new SocketIOServer(config);
    }

	public static void main(String[] args) {
		SpringApplication.run(SocketApplication.class, args);
	}

}
