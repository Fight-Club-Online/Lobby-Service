package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.SeeUserCharactersUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateUserCharacterUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user-characters")
public class UserCharacterController {
    private final SeeUserCharactersUseCase seeUserCharactersUseCase;
    private final CreateUserCharacterUseCase createUserCharacterUseCase;

    @GetMapping("user/characters")
    public ResponseEntity<List<UserCharacter>> seeUserCharacters(@RequestParam String userId){
        List<UserCharacter> characters = seeUserCharactersUseCase.seeUserCharacters(userId);
        return ResponseEntity.ok(characters);
    }

    @PostMapping("/create")
    public ResponseEntity<UserCharacter> createUserCharacter(@RequestBody CreateUserCharacterDTO dto){
        UserCharacter createdCharacter = createUserCharacterUseCase.createUserCharacter(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
    }
}
