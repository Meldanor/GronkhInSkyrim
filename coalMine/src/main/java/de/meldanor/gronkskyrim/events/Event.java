package de.meldanor.gronkskyrim.events;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import de.meldanor.gronkskyrim.data.EpisodeMoment;
import de.meldanor.gronkskyrim.data.EventData;
import de.meldanor.gronkskyrim.data.PlayerGold;
import de.meldanor.gronkskyrim.data.PlayerWeight;
import de.meldanor.gronkskyrim.serialize.dto.EventDto;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class Event {
    private final EpisodeMoment moment;
    private PlayerWeight playerWeight;
    private PlayerGold playerGold;

    public Event(EpisodeMoment moment) {
        this.moment = moment;
    }

    public void appendData(EventData<?> eventData) {
        if (eventData == null) {
            return;
        }
        if (eventData.getEventType() == EventType.PLAYER_WEIGHT) {
            this.playerWeight = (PlayerWeight) eventData;
        } else if (eventData.getEventType() == EventType.PLAYER_GOLD) {
            this.playerGold = (PlayerGold) eventData;
        } else {
            throw new RuntimeException("Unsupported event data!");
        }
    }

    public List<EventData<?>> getData() {
        List<EventData<?>> data = new ArrayList<>();
        if (this.playerWeight != null) {
            data.add(playerWeight);
        }
        if (this.playerGold != null) {
            data.add(playerGold);
        }

        return data;
    }

    public EventData<?> getDatum(EventType type) {
        if (type == EventType.PLAYER_WEIGHT) {
            return this.playerWeight;
        } else if (type == EventType.PLAYER_GOLD) {
            return this.playerGold;
        }
        throw new RuntimeException("Unsupported event data!");
    }

    public double getFrameTime() {
        return this.moment.episodeSecond();
    }

    @Override
    public String toString() {
        return "Event{" +
                "moment=" + moment +
                ", playerWeight=" + playerWeight +
                ", playerGold=" + playerGold +
                '}';
    }


    private final static HashFunction HASH_FUNCTION = Hashing.murmur3_32();

    public int calculateHash() {
        Hasher hasher = HASH_FUNCTION.newHasher();
        if (this.playerGold != null) {
            hasher.putInt(this.playerGold.getGold());
        } else {
            hasher.putInt(-1);
        }

        if (this.playerWeight != null) {
            hasher.putInt(this.playerWeight.getCurrentWeight());
            hasher.putInt(this.playerWeight.getMaximumWeight());
        } else {
            hasher.putInt(-1);
        }
        return hasher.hash().asInt();
    }

    public EventDto toSerializable() {
        return new EventDto(this);
    }
}
