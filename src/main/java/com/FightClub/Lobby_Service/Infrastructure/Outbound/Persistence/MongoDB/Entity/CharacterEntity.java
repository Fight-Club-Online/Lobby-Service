package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterEntity {
    private long characterId;
    private int characterLevel;
    private String characterName;
    private String characterHp;
    private String characterATK;
    private String characterDEF;
}
