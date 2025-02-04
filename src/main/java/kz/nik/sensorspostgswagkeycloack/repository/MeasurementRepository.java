package kz.nik.sensorspostgswagkeycloack.repository;

import kz.nik.sensorspostgswagkeycloack.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    long countByRainingTrue();
}