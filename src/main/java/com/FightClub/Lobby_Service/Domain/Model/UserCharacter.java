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
    private String characterId;           
    private String characterName;       
    private int characterLevel;        
    private int characterHp;          
    private int characterATK;         
    private int characterDEF;        
    private CharacterAssets assets;     
}
