package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Supplier;
import net.juanxxiii.dto.ClienteCompletoDto;
import net.juanxxiii.dto.ClienteTelefonoDto;
import net.juanxxiii.dto.ProveedorCompletoDto;
import net.juanxxiii.dto.ProveedorTelefonoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    //SELECT t.idTelefono, t.numTelefono FROM Supplier s JOIN telefonosProveedor t on s.idProveedor = t.IdProveedor
    //SELECT new net.juanxxiii.dto.TelephoneDto(t.id, t.number) FROM Supplier s INNER JOIN Telephone t ON t.supplier = s.id

    //ES NECESARIO USAR ALIAS PARA LAS TABLAS
    @Query(value = "SELECT new net.juanxxiii.dto.ProveedorTelefonoDto(s.id, s.name, t.id, t.number) FROM Supplier s INNER JOIN SupplierTelephone t ON t.suplier = s.id")
    List<ProveedorTelefonoDto> fetchTelephoneInnerJoin();

    @Query(value = "SELECT new net.juanxxiii.dto.ProveedorCompletoDto(s.id, s.name, d.id, d.direction, t.id, t.number) FROM Supplier s INNER JOIN SupplierDirection d ON s.id = d.suplier INNER JOIN SupplierTelephone t ON s.id = t.suplier")
    List<ProveedorCompletoDto> fetchFullSupplierJoin();

    @Transactional
    @Modifying
    @Query("UPDATE Supplier s SET s.name = :name, s.dni = :dni, s.email = :email WHERE s.id = :id")
    int updateSupplier(@Param("name") String name,@Param("dni") String dni,@Param("email") String email,@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Supplier s SET s.name = :name WHERE s.id = :id")
    int updateSupplierName(@Param("name") String name, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Supplier s SET s.dni = :dni WHERE s.id = :id")
    int updateSupplierDni(@Param("dni") String dni, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Supplier s SET s.email = :email WHERE s.id = :id")
    int updateSupplierEmail(@Param("email") String email, @Param("id") int id);

}
