package de.meldanor.gronkskyrim.serialize.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlayerGoldDto.class, name = "gold"),
        @JsonSubTypes.Type(value = PlayerWeightDto.class, name = "weight")
})
public abstract class EventDataDto {
    public EventDataDto() {

    }
}
