package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.SupplierDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDirectionRepository extends JpaRepository<SupplierDirection, Integer> {
}
