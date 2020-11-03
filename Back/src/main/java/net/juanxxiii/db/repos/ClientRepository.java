package net.juanxxiii.db.repos;

import net.juanxxiii.db.tables.Client;
import net.juanxxiii.dto.TelephoneDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientRepository extends JpaRepository<Client, Integer> {
    //SELECT * FROM clientes JOIN telefonoscliente t on clientes.idCliente = t.IdCliente
    //"SELECT new TelephoneDto(t.IdTelefono, t.numTelefono) FROM clientes c INNER JOIN telefonoscliente t"
    @Query(value = "SELECT t.IdTelefono, t.numTelefono FROM clientes c JOIN telefonoscliente t on c.idCliente = t.IdCliente",
    nativeQuery = true)
    List<TelephoneDto> fetchTelephoneInnerJoin();
}
