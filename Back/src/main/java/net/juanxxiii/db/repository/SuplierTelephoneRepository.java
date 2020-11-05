package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.SuplierTelephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplierTelephoneRepository extends JpaRepository<SuplierTelephone, Integer> {
}
