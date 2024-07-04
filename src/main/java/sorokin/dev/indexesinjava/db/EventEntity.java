package sorokin.dev.indexesinjava.db;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "events")
public class EventEntity {
    /**
     * Id мероприятия
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Дата старта события
     */
    @Column(name = "event_date")
    private LocalDateTime eventDate;
    /**
     * Длительность мероприятия в часах
     */
    @Column(name = "duration")
    private int duration;
    /**
     * Стоимость мероприятия
     */
    @Column(name = "cost")
    private int cost;
    /**
     * Максимальное количество мест
     */
    @Column(name = "max_places")
    private int maxPlaces;
    /**
     * Id локации
     */
    @Column(name = "location_id")
    private Long locationId;
    /**
     * Название мероприятия
     */
    @Column(name = "name")
    private String name;
    /**
     * Статус мероприятия
     */
    @Column(name = "event_status")
    private String eventStatus;
    /**
     * Id владельца
     */
    @Column(name = "owner_id")
    private Long ownerId;
    /**
     * Количество занятых мест
     */
    @Column(name = "occupied_places")
    private int occupiedPlaces;
}



