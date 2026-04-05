package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CharacterAssetService {
    
    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;
    
    /**
     * Genera las URLs de assets para un personaje
     */
    public CharacterAssets generateAssetUrls() {
        return CharacterAssets.fromBaseUrl(baseUrl);
    }
    
    /**
     * Obtiene una URL de asset específico
     */
    public String getAssetUrl(String assetFileName) {
        return baseUrl + "/assets/character/" + assetFileName;
    }
}
