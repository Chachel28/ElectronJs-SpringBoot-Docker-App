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
    int lastId();

    @Modifying
    @Transactional
    @Query(value = "UPDATE compras SET idFactura=:idfactura WHERE idCompra=:id",nativeQuery = true)
    void updateIdReceipt(int id, int idfactura);

    @Query("UPDATE Purchase p SET p.supplier = :supplier WHERE p.id = :id")
    int updatePurchase(@Param("supplier") int supplier, @Param("id") int id);
}
