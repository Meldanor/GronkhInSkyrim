package de.meldanor.gronkskyrim.events;

import de.meldanor.gronkskyrim.Config;
import de.meldanor.gronkskyrim.data.EpisodeBase;
import de.meldanor.gronkskyrim.data.EpisodeMoment;
import de.meldanor.gronkskyrim.data.EventData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class EventParser {
    private static final EventParser INSTANCE = new EventParser();

    private EventParser() {

    }

    public static EventParser getInstance() {
        return INSTANCE;
    }

    public Event parse(EpisodeBase episodeBase, String line) {
        int index = parseIndex(line);
        Event event = new Event(new EpisodeMoment(episodeBase, index));
        parseData(event, line);
        return event;
    }

    private static final Pattern INDEX_PATTERN = Pattern.compile("EPISODE_TIME_STAMP=(\\d+)");

    private int parseIndex(String line) {
        Matcher matcher = INDEX_PATTERN.matcher(line);
        if (matcher.find()) {
            return (Integer.parseInt(matcher.group(1)) * Config.OCR_FRAMES_PER_SECOND);
        } else {
            throw new RuntimeException("Can't find index in line '" + line + "'");
        }
    }

    private static final List<Method> FROM_EVENT_LOG_STRING_METHODS;

    static {
        FROM_EVENT_LOG_STRING_METHODS = Stream.of(EventType.values())
                .map(eventType -> {
                            try {
                                return eventType.getClazz().getDeclaredMethod("fromEventLogString", String.class);
                            } catch (NoSuchMethodException e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .collect(Collectors.toList());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void parseData(Event event, String line) {
        for (Method method : FROM_EVENT_LOG_STRING_METHODS) {
            Optional<? extends EventData> eventData;
            try {
                eventData = (Optional<? extends EventData>) method.invoke(null, line);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            eventData.ifPresent(event::appendData);
        }
    }
}
