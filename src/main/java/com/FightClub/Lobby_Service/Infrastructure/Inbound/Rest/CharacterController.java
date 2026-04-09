package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.DTO.CreateCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateCharacterUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.SeeCharactersUseCase;
import com.FightClub.Lobby_Service.Domain.Model.Character;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/characters")
public class CharacterController {

    private final CreateCharacterUseCase createCharacterUseCase;
    private final SeeCharactersUseCase seeCharactersUseCase;

    @GetMapping("/all")
    public ResponseEntity<List<Character>> seeAllCharacters() {
        List<Character> characters = seeCharactersUseCase.seeAllCharacters();
        return ResponseEntity.ok(characters);
    }

    @PostMapping("/create")
    public ResponseEntity<Character> createCharacter(@RequestBody CreateCharacterDTO dto) {
        Character createdCharacter = createCharacterUseCase.createCharacter(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
    }
}
