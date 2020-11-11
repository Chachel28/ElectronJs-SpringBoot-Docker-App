package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.ClientDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDirectionRepository extends JpaRepository<ClientDirection, Integer> {
}
