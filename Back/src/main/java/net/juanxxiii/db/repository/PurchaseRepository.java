package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    @Query("SELECT MAX(p.id) FROM Purchase p")
    int lastId();

    @Query("UPDATE Purchase p SET p.supplier = :supplier WHERE p.id = :id")
    int updatePurchase(@Param("supplier") int supplier,@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sge_moviles.compras SET idPersonal=:idpersonal WHERE idCompra=:id",nativeQuery = true)
    void updateIdStaff(@Param("idpersonal") int idStaff, @Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sge_moviles.compras SET idFactura=:idfactura WHERE idCompra=:id",nativeQuery = true)
    void updateIdReceipt(@Param("idfactura")int idReceipt,@Param("id") int id1);


}
