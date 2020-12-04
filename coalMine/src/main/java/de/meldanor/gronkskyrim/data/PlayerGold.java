package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.ocr.ParseException;
import de.meldanor.gronkskyrim.serialize.dto.EventDataDto;
import de.meldanor.gronkskyrim.serialize.dto.PlayerGoldDto;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerGold implements EventData<PlayerGold> {

    private final int gold;

    private static final Pattern GOLD_PATTERN = Pattern.compile("Gold\\s*(\\d+)");

    public PlayerGold(String ocrText) {
        Matcher matcher = GOLD_PATTERN.matcher(ocrText);
        if (matcher.find()) {
            this.gold = Integer.parseInt(matcher.group(1).strip());
        } else {
            throw new ParseException(ocrText);
        }
    }

    public PlayerGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    @Override
    public String toString() {
        return String.valueOf(gold);
    }

    @Override
    public EventType getEventType() {
        return EventType.PLAYER_GOLD;
    }

    @Override
    public EventDataDto toSerializable() {
        return new PlayerGoldDto(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerGold that = (PlayerGold) o;
        return gold == that.gold;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gold);
    }
}
