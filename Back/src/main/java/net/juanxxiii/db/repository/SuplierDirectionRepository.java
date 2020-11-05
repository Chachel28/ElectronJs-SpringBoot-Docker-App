package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.SuplierDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplierDirectionRepository extends JpaRepository<SuplierDirection, Integer> {
}
