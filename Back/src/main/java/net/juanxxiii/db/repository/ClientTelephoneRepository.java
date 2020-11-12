package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.ClientTelephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientTelephoneRepository extends JpaRepository<ClientTelephone, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM ClientTelephone ct WHERE ct.id = :id")
    void deleteById(@Param("id") int id);
}
