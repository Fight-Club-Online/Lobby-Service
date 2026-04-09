package com.FightClub.Lobby_Service.Application.Service.Characters;

import com.FightClub.Lobby_Service.Application.Ports.Input.Character.SeeCharactersUseCase;
import com.FightClub.Lobby_Service.Domain.Model.Character;
import com.FightClub.Lobby_Service.Domain.Repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeeCharactersImpl implements SeeCharactersUseCase {

    private final CharacterRepository characterRepository;

    @Override
    public List<Character> seeAllCharacters() {
        return characterRepository.findAllCharacters();
    }
}
