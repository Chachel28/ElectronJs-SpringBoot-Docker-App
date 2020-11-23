package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.SupplierTelephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SupplierTelephoneRepository extends JpaRepository<SupplierTelephone, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM SupplierTelephone st WHERE st.id = :id")
    void deleteById(@Param("id") int id);
}
