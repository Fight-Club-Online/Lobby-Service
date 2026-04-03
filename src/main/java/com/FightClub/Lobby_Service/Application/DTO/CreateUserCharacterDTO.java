package com.FightClub.Lobby_Service.Application.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserCharacterDTO {
    private String userId;
    private String characterId;
    private String characterName;
    private int characterLevel;
    private int characterHp;
    private int characterATK;
    private int characterDEF;
}
