package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Purcharse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurcharseRepository extends JpaRepository<Purcharse, Integer> {
}
