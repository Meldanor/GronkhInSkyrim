package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.ocr.ParseException;

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

    @Override
    public String toString() {
        return String.valueOf(gold);
    }

    @Override
    public String toEventLogString() {
        return toString();
    }

    @Override
    public PlayerGold fromEventLogString(String eventLogString) {
        return new PlayerGold(eventLogString);
    }

    @Override
    public EventType getEventType() {
        return EventType.PLAYER_GOLD;
    }
}
