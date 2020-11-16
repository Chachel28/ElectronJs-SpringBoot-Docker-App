package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    @Query("Select max(s.id) from Sale s")
    int lastId();

    @Query("UPDATE Sale s SET s.client = :client WHERE s.id = :id")
    int updateSale(@Param("client") int client,@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ventas SET idPersonal=:idpersonal WHERE idVenta=:id",nativeQuery = true)
    void updateIdStaff(@Param("idpersonal") int idStaff, @Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ventas SET idFactura=:idfactura WHERE idVenta=:id",nativeQuery = true)
    void updateIdReceipt(@Param("idfactura")int idReceipt,@Param("id") int id1);
}
