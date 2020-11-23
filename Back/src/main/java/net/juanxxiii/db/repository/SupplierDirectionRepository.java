package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.SupplierDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SupplierDirectionRepository extends JpaRepository<SupplierDirection, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM SupplierDirection sd WHERE sd.id = :id")
    void deleteById(@Param("id") int id);
}
