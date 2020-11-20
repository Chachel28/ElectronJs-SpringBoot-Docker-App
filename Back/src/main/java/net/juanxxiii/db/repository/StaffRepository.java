package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Staff s SET s.name= :name, s.email=:email, s.password=:password, s.telephone=:telephone WHERE s.idStaff = :id")
    int updateStaff(@Param("name") String name, @Param("email") String email, @Param("password") String password, @Param("telephone") int telephone, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Staff s SET s.name = :name WHERE s.idStaff=:id")
    void updateStaffName(@Param("name") String name, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Staff s SET s.email = :email WHERE s.idStaff=:id")
    void updateStaffEmail(@Param("email") String email, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Staff s SET s.password = :password WHERE s.idStaff=:id")
    void updateStaffPassword(@Param("password") String password, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Staff s SET s.telephone = :telephone WHERE s.idStaff=:id")
    void updateStaffTelephone(@Param("telephone") int telephone, @Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE personal SET idPuesto=:idpuesto WHERE idPersonal=:id",nativeQuery = true)
    void updateIdPositionStaff(@Param("idpuesto") int idPosition, @Param("id") int id);

    @Query("select s from Staff s where s.name = :name")
    Optional<Staff> findByName(@Param("name")String name);
}
