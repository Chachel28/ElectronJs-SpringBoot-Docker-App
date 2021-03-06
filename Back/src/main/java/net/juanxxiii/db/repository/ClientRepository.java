package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Client;
import net.juanxxiii.dto.ClienteCompletoDto;
import net.juanxxiii.dto.ClienteTelefonoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    //SELECT t.idTelefono, t.numTelefono FROM clientes c JOIN telefonoscliente t on c.idCliente = t.IdCliente
    //SELECT new net.juanxxiii.dto.TelephoneDto(t.id, t.number) FROM Client c INNER JOIN Telephone t ON t.client = c.id

    //ES NECESARIO USAR ALIAS PARA LAS TABLAS
    @Query(value = "SELECT new net.juanxxiii.dto.ClienteTelefonoDto(c.id, c.fullName, t.id, t.number) FROM Client c INNER JOIN ClientTelephone t ON t.client = c.id")
    List<ClienteTelefonoDto> fetchTelephoneInnerJoin();

    @Query(value = "SELECT new net.juanxxiii.dto.ClienteCompletoDto(c.id, c.fullName, d.id, d.direction, t.id, t.number) FROM Client c INNER JOIN ClientDirection d ON c.id = d.client INNER JOIN ClientTelephone t ON c.id = t.client")
    List<ClienteCompletoDto> fetchFullClientJoin();

    @Query("SELECT MAX(c.id) FROM Client c")
    int lastId();

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.fullName = :fullName, c.dni = :dni, c.email = :email, c.iban = :iban WHERE c.id = :id")
    int updateClient(@Param("fullName") String fullName,@Param("dni") String dni,@Param("email") String email,@Param("iban") String iban,@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.fullName = :fullName WHERE c.id = :id")
    int updateClientName(@Param("fullName") String fullName, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.dni = :dni WHERE c.id = :id")
    int updateClientDni(@Param("dni") String dni, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.email = :email WHERE c.id = :id")
    int updateClientEmail(@Param("email") String email, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.iban = :iban WHERE c.id = :id")
    int updateClientIban(@Param("iban") String iban, @Param("id") int id);
}
