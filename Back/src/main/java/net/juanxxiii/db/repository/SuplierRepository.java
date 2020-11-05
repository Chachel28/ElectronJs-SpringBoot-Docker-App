package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Suplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplierRepository extends JpaRepository<Suplier, Integer> {
}
