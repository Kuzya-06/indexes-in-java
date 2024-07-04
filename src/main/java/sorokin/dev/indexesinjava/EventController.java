package sorokin.dev.indexesinjava;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sorokin.dev.indexesinjava.db.EventEntity;
import sorokin.dev.indexesinjava.db.EventRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventRepository repository;
    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<Event> createEvent(
            @RequestBody Event eventToCreate
    ) {
        var entity = eventMapper.toEntity(eventToCreate);
        var saved = repository.save(entity);

        return ResponseEntity.ok(eventMapper.toDomain(saved));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Event>> searchEvent(
            EventSearchFilter eventSearchFilter
    ) {
        log.info("Called search by filter");
        var startTime = System.currentTimeMillis();
        List<EventEntity> foundEntities = repository.getEventsByFilter(
                eventSearchFilter.getDurationMax(),
                eventSearchFilter.getDurationMin(),
                eventSearchFilter.getPlacesMin(),
                eventSearchFilter.getPlacesMax(),
                eventSearchFilter.getLocationId(),
                eventSearchFilter.getEventStatus(),
                eventSearchFilter.getName(),
                eventSearchFilter.getCostMin(),
                eventSearchFilter.getCostMax(),
                Pageable.ofSize(eventSearchFilter.getPageSize())
                        .withPage(eventSearchFilter.getPageNumber())
        );
        var endTime = System.currentTimeMillis();
        log.info("Search by filter ended: searchTimeMS={}", endTime - startTime);

        var domainEvents = foundEntities.stream()
                .map(eventMapper::toDomain).toList();
        return ResponseEntity.ok(domainEvents);
    }

    @PostMapping("/init")
    public void init(
            @RequestParam("batch-count") Integer batchCount,
            @RequestParam("batch-size") Integer batchSize
    ) {
        log.info("Get request for generating events: batchCount={}, batchSize={}", batchCount, batchSize);
        Random random = new Random();

        for (int i = 0; i < batchCount; i++) {
            List<EventEntity> events = new ArrayList<>();
            for (int j = 0; j < batchSize; j++) {
                EventEntity event = EventEntity.builder()
                        .eventDate(LocalDateTime.now().plusDays(random.nextInt(36_500)))
                        .duration(random.nextInt(24) + 1)
                        .cost(random.nextInt(1_000_000) + 100)
                        .maxPlaces(random.nextInt(50_000) + 50)
                        .locationId((long) random.nextInt(1_000_000) + 1)
                        .name("Event " + i)
                        .eventStatus(random.nextBoolean() ? "ACTIVE" : "INACTIVE")
                        .ownerId((long) random.nextInt(1_000_000) + 1)
                        .occupiedPlaces(random.nextInt(5_000_000))
                        .build();
                events.add(event);
            }
            var startTime = System.currentTimeMillis();
            repository.saveAll(events);
            var endTime = System.currentTimeMillis();
            log.info("Saved batch: batchNum={}, dbSavingTimeMS={}", i + 1, endTime - startTime);
        }
    }
}
