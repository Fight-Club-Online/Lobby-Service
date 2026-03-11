package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Document(collection = "user_characters")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserCharacterEntity {
    @Id
    private String id;
    private Character character;
    private String user;
}
