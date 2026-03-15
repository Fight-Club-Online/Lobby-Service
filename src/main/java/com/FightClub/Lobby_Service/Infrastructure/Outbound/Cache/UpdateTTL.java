package com.FightClub.Lobby_Service.Infrastructure.Outbound.Cache;

import com.FightClub.Lobby_Service.Domain.Model.Room;

public interface UpdateTTL {
    void updateTTL(Room room);
}
