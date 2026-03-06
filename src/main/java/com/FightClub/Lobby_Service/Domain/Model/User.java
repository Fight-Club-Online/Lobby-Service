package com.FightClub.Lobby_Service.Domain.Model;

import com.FightClub.Lobby_Service.Domain.Model.Notifications.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NonNull
    private String userId;
    @NonNull
    private String username;
    private List<Character> characters = new ArrayList<>();
    private List<Friend> friends = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();
}
