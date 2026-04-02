package com.FightClub.Lobby_Service.Infrastructure.Config;

import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Mapper.CharacterMapper;
import com.FightClub.Lobby_Service.Infrastructure.Outbound.Persistence.MongoDB.Mapper.UserCharacterMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public CharacterMapper characterMapper() {
        return Mappers.getMapper(CharacterMapper.class);
    }

    @Bean
    public UserCharacterMapper userCharacterMapper() {
        return Mappers.getMapper(UserCharacterMapper.class);
    }
}
