package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.data.PlayerWeight;

public enum EventType {
    PLAYER_WEIGHT(PlayerWeight.class);

    private final Class<PlayerWeight> clazz;

    EventType(Class<PlayerWeight> clazz) {
        this.clazz = clazz;
    }

    public Class<PlayerWeight> getClazz() {
        return clazz;
    }
}
