package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.PositionStaff;
import net.juanxxiii.db.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Staff s SET s.name= :name, s.email=:email, s.password=:password WHERE s.idStaff = :id")
    int updateStaff(@Param("name") String name, @Param("email") String email, @Param("password") String password, @Param("id") int id);
}
