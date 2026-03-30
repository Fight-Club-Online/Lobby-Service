package com.FightClub.Lobby_Service.Domain.Model.Enums;

public enum AnimationType {
    IDLE("IDLE.png"),
    RUN("RUN.png"),
    ATTACK("ATTACK 1.png"),
    HURT("HURT.png");

    private final String fileName;

    AnimationType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAssetUrl(String baseUrl) {
        return baseUrl + "/assets/character/" + fileName;
    }
}
