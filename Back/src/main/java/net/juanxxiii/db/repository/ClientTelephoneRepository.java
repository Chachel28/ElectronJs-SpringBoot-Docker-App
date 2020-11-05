package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.ClientTelephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTelephoneRepository extends JpaRepository<ClientTelephone, Integer> {
}
