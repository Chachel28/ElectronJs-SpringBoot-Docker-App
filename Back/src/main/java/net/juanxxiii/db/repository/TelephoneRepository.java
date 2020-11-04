package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Integer> {
}
