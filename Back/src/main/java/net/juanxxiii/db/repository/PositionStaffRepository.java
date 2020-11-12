package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.PositionStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionStaffRepository extends JpaRepository<PositionStaff, Integer> {
    @Query("select p from PositionStaff p where p.name = :name")
    Optional<PositionStaff> findByName(@Param("name")String name);
}
