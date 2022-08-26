package it.uniroma1.commons.repository;

import it.uniroma1.commons.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface FineRepository extends JpaRepository<Fine, Long> {
    @Query(
            value = "SELECT * FROM fines f WHERE f.manager_code IS null AND f.speed_camera_id IN (SELECT id FROM speed_cameras WHERE region=?1)",
            nativeQuery = true)
    Collection<Fine> findAllNewFines(String region);

    @Query(
            value = "SELECT * FROM fines f WHERE f.manager_code IS NOT null AND f.speed_camera_id IN (SELECT id FROM speed_cameras WHERE region=?1)",
            nativeQuery = true)
    Collection<Fine> findAllManagedFines(String region);

}
