package sorokin.dev.indexesinjava;

import org.springframework.stereotype.Component;
import sorokin.dev.indexesinjava.db.EventEntity;

import java.sql.Timestamp;

@Component
public class EventMapper {
    public Event toDomain(EventEntity eventEntity) {
        if (eventEntity == null) {
            return null;
        }
        return Event.builder()
                .id(eventEntity.getId())
                .locationId(eventEntity.getLocationId())
                .cost(eventEntity.getCost())
                .duration(eventEntity.getDuration())
                .eventDate(eventEntity.getEventDate())
                .ownerId(eventEntity.getOwnerId())
                .eventStatus(eventEntity.getEventStatus())
                .occupiedPlaces(eventEntity.getOccupiedPlaces())
                .maxPlaces(eventEntity.getMaxPlaces())
                .name(eventEntity.getName())
                .build();
    }

    public EventEntity toEntity(Event event) {
        if (event == null) {
            return null;
        }
        return EventEntity.builder()
                .id(event.getId())
                .cost(event.getCost())
                .duration(event.getDuration())
                .eventDate(event.getEventDate())
                .ownerId(event.getOwnerId())
                .eventStatus(event.getEventStatus())
                .occupiedPlaces(event.getOccupiedPlaces())
                .maxPlaces(event.getMaxPlaces())
                .name(event.getName())
                .build();
    }
}
