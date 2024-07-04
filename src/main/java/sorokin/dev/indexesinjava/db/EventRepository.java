package sorokin.dev.indexesinjava.db;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("""
            select e from EventEntity e
            where (:durationMin is null or e.duration >= :durationMin)
            and (:durationMax is null or e.duration <= :durationMax)
            and (:placesMin is null or e.maxPlaces >= :placesMin)
            and (:placesMax is null or e.maxPlaces <= :placesMax)
            and (:locationId is null or e.locationId = :locationId)
            and (:eventStatus is null or e.eventStatus = :eventStatus)
            and (:name is null or e.name like %:name%)
            and (:costMin is null or e.cost >= :costMin)
            and (:costMax is null or e.cost <= :costMax)
            """)
    List<EventEntity> getEventsByFilter(@Param(value = "durationMax") Integer durationMax,
                                        @Param(value = "durationMin") Integer durationMin,
                                        @Param(value = "placesMin") Integer placesMin,
                                        @Param(value = "placesMax") Integer placesMax,
                                        @Param(value = "locationId") Long locationId,
                                        @Param(value = "eventStatus") String eventStatus,
                                        @Param(value = "name") String name,
                                        @Param("costMin") Integer costMin,
                                        @Param("costMax") Integer costMax,
                                        Pageable pageable);

}

