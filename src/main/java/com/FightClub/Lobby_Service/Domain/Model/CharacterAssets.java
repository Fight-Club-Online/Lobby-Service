package com.FightClub.Lobby_Service.Domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterAssets {
    
    @JsonProperty("idle_url")
    private String idleUrl;
    
    @JsonProperty("run_url")
    private String runUrl;
    
    @JsonProperty("attack_url")
    private String attackUrl;
    
    @JsonProperty("hurt_url")
    private String hurtUrl;
    
    // Constructor de conveniencia para crear desde base URL
    public static CharacterAssets fromBaseUrl(String baseUrl) {
        return CharacterAssets.builder()
                .idleUrl(baseUrl + "/assets/character/IDLE.png")
                .runUrl(baseUrl + "/assets/character/RUN.png")
                .attackUrl(baseUrl + "/assets/character/ATTACK 1.png")
                .hurtUrl(baseUrl + "/assets/character/HURT.png")
                .build();
    }
}
