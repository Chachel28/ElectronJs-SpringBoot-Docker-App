package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Client;
import net.juanxxiii.dto.ClienteTelefonoDto;
import net.juanxxiii.dto.TelephoneDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientRepository extends JpaRepository<Client, Integer> {
    //SELECT t.idTelefono, t.numTelefono FROM clientes c JOIN telefonoscliente t on c.idCliente = t.IdCliente
    //SELECT new net.juanxxiii.dto.TelephoneDto(t.id, t.number) FROM Client c INNER JOIN Telephone t
    @Query(value = "SELECT new net.juanxxiii.dto.ClienteTelefonoDto(c.id, c.fullName, t.id, t.number) FROM Client c INNER JOIN Telephone t ON t.client = c.id")
    List<ClienteTelefonoDto> fetchTelephoneInnerJoin();
}
