package com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterAssetsEntity {
    
    @Field("idle_url")
    @JsonProperty("idle_url")
    private String idleUrl;
    
    @Field("run_url")
    @JsonProperty("run_url")
    private String runUrl;
    
    @Field("attack_url")
    @JsonProperty("attack_url")
    private String attackUrl;
    
    @Field("hurt_url")
    @JsonProperty("hurt_url")
    private String hurtUrl;
}
