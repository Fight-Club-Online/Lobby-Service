package com.FightClub.Lobby_Service.Application.Ports.Output;

import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface SendMessageProducer {
    void sendRoom(Room room);
}
