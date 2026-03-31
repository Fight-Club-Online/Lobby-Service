package com.FightClub.Lobby_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LobbyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LobbyServiceApplication.class, args);
	}

}
