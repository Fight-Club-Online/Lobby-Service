package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.DTO.CreateUserCharacterDTO;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.SeeUserCharactersUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.CreateUserCharacterUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.DeleteUserCharacterUseCase;
import com.FightClub.Lobby_Service.Application.Ports.Input.Character.GetUserCharacterAssetsUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import com.FightClub.Lobby_Service.Domain.Model.CharacterAssets;
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
    private final DeleteUserCharacterUseCase deleteUserCharacterUseCase;
    private final GetUserCharacterAssetsUseCase getUserCharacterAssetsUseCase;

    @GetMapping("/user/characters")
    public ResponseEntity<List<UserCharacter>> seeUserCharacters(@RequestParam String userId){
        List<UserCharacter> characters = seeUserCharactersUseCase.seeUserCharacters(userId);
        return ResponseEntity.ok(characters);
    }


    @PostMapping("/create")
    public ResponseEntity<UserCharacter> createUserCharacter(@RequestBody CreateUserCharacterDTO dto){
        UserCharacter createdCharacter = createUserCharacterUseCase.createUserCharacter(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
    }


    @DeleteMapping("/{characterId}")
    public ResponseEntity<Void> deleteUserCharacter(
            @RequestParam String userId,
            @PathVariable String characterId){
        deleteUserCharacterUseCase.deleteUserCharacter(userId, characterId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{characterId}/assets")
    public ResponseEntity<CharacterAssets> getUserCharacterAssets(
            @RequestParam String userId,
            @PathVariable String characterId){
        CharacterAssets assets = getUserCharacterAssetsUseCase.getUserCharacterAssets(userId, characterId);
        if (assets != null) {
            return ResponseEntity.ok(assets);
        }
        return ResponseEntity.notFound().build();
    }
}
