package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import com.FightClub.Lobby_Service.Application.Ports.Input.Character.SeeUserCharactersUseCase;
import com.FightClub.Lobby_Service.Domain.Model.UserCharacter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user-characters")
public class UserCharacterController {
    private final SeeUserCharactersUseCase seeUserCharactersUseCase;

    @GetMapping("/characters")
    public List<UserCharacter> seeUserCharacters(String userId){
        return seeUserCharactersUseCase.seeUserCharacters(userId);
    }
}
