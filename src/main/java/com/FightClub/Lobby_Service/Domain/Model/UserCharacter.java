package com.FightClub.Lobby_Service.Domain.Model;

import io.micrometer.core.instrument.Meter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCharacter {
    
    private String id;
    private Character character;
    private String user;
    private CharacterAssets assets;
}
