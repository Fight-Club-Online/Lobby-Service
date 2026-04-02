package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_characters")
public class UserCharacterEntity {

    private String userId;              
    @Id
    private Long characterId;           
    private Long characterLevel;       
    private String characterName;       
    private String characterHp;        
    private String characterATK;        
    private String characterDEF;       

    private CharacterAssetsEntity assets;

}