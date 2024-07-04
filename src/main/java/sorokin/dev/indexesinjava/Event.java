package sorokin.dev.indexesinjava;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Представляет событие.
 */
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Event {
    /**
     * ID события.
     */
    private final Long id;
    /**
     * Дата и время проведения события.
     */
    private final LocalDateTime eventDate;
    /**
     * Продолжительность события в минутах.
     */
    private final int duration;
    /**
     * Стоимость события.
     */
    private final int cost;
    /**
     * Максимальное количество мест для проведения события.
     */
    private final int maxPlaces;
    /**
     * ID местоположения проведения события.
     */
    private final Long locationId;
    /**
     * Название события.
     */
    private final String name;
    /**
     * Статус события.
     */
    private final String eventStatus;
    /**
     * ID владельца события.
     */
    private final Long ownerId;
    /**
     * Количество занятых мест для проведения события.
     */
    private final int occupiedPlaces;
}


