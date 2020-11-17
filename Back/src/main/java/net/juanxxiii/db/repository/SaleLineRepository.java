package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.SaleLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleLineRepository extends JpaRepository<SaleLine, Integer> {
}
