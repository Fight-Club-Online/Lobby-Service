package com.FightClub.Lobby_Service.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCharacter {
    
    private String userId;              
    private Long characterId;           
    private String characterName;       
    private Long characterLevel;        
    private String characterHp;          
    private String characterATK;         
    private String characterDEF;        
    private CharacterAssets assets;     
}
