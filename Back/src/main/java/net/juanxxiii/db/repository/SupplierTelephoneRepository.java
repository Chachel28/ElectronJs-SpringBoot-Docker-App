package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.SupplierTelephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierTelephoneRepository extends JpaRepository<SupplierTelephone, Integer> {
}
