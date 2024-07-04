package sorokin.dev.indexesinjava;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Представляет фильтр для поиска событий.
 */
@AllArgsConstructor
@Setter
@Getter
@Builder
public class EventSearchFilter {
    /**
     * Максимальная длительность события.
     */
    private final Integer durationMax;
    /**
     * Минимальная длительность события.
     */
    private final Integer durationMin;
    /**
     * Минимальное количество свободных мест для события.
     */
    private final Integer placesMin;
    /**
     * Максимальное количество свободных мест для события.
     */
    private final Integer placesMax;
    /**
     * Идентификатор местоположения события.
     */
    private final Long locationId;
    /**
     * Статус события.
     */
    private final String eventStatus;
    /**
     * Название события.
     */
    private final String name;
    /**
     * Минимальная стоимость события.
     */
    private final Integer costMin;
    /**
     * Максимальная стоимость события.
     */
    private final Integer costMax;
    /**
     * Номер страницы.
     */
    private final Integer pageNumber;
    /**
     * Размер страницы.
     */
    private final Integer pageSize;
}