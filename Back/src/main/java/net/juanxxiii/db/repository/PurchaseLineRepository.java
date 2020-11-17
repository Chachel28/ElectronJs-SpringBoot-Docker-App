package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.PurchaseLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLine, Integer> {
    @Query("Select max(p.id) from Purchase p")
    int lastId();
}
