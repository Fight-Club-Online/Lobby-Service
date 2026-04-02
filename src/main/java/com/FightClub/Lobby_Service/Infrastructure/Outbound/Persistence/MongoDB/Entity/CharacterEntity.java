package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "characters")
public class CharacterEntity {
    @Id
    private long characterId;
    private Long characterLevel;
    private String characterName;
    private String characterHp;
    private String characterATK;
    private String characterDEF;        // Stats por defecto del template
}
