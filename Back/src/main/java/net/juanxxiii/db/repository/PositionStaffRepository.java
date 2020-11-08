package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.PositionStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionStaffRepository extends JpaRepository<PositionStaff, Integer> {
}
