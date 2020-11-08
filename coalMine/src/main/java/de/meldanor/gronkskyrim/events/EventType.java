package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.data.EventData;
import de.meldanor.gronkskyrim.data.PlayerGold;
import de.meldanor.gronkskyrim.data.PlayerWeight;

public enum EventType {
    PLAYER_WEIGHT(PlayerWeight.class),
    PLAYER_GOLD(PlayerGold.class);

    private final Class<? extends EventData<?>> clazz;

    EventType(Class<? extends EventData<?>> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends EventData<?>> getClazz() {
        return clazz;
    }
}
