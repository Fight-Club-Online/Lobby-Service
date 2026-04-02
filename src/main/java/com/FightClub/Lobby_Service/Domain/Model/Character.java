package com.FightClub.Lobby_Service.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Character {
    private long characterId;
    private Long characterLevel;
    private String characterName;
    private String characterHp;
    private String characterATK;
    private String characterDEF;
}
