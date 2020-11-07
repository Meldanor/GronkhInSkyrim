package de.meldanor.gronkskyrim.data;

import de.meldanor.gronkskyrim.events.EventType;
import de.meldanor.gronkskyrim.ocr.ParseException;

import java.util.Optional;
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

    private PlayerGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return String.valueOf(gold);
    }

    @Override
    public String toEventLogString() {
        return toString();
    }

    private static final Pattern EVENT_LOG_PATTERN = Pattern.compile("PLAYER_GOLD=(\\d+)");

    public static Optional<PlayerGold> fromEventLogString(String eventLogString) {
        Matcher matcher = EVENT_LOG_PATTERN.matcher(eventLogString);
        if (matcher.find()) {
            int gold = Integer.parseInt(matcher.group(1));
            return Optional.of(new PlayerGold(gold));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public EventType getEventType() {
        return EventType.PLAYER_GOLD;
    }
}
