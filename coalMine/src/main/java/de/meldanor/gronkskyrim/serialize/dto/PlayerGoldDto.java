package de.meldanor.gronkskyrim.serialize.dto;

import de.meldanor.gronkskyrim.data.PlayerGold;

public class PlayerGoldDto extends EventDataDto {
    private int gold;

    public PlayerGoldDto() {
    }

    public PlayerGoldDto(PlayerGold original) {
        this.gold = original.getGold();
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public PlayerGold toOriginal() {
        return new PlayerGold(this.gold);
    }
}
