package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Receipt r SET r.receiptDate=:receiptDate, r.subtotal=:subtotal, r.discounts=:discounts, r.iva=:iva, r.total=:total WHERE r.id=:id")
    int updateReceipt(@Param("receiptDate") String receiptDate, @Param("subtotal") float subtotal, @Param("discounts") float discounts,@Param("iva") float iva, @Param("total") float total, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Receipt r SET r.receiptDate=:receiptDate WHERE r.id=:id")
    int updateReceiptDate(@Param("receiptDate") String receiptDate, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Receipt r SET r.subtotal=:subtotal WHERE r.id=:id")
    int updateSubtotal(@Param("subtotal") float subtotal, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Receipt r SET r.discounts=:discounts WHERE r.id=:id")
    int updateDiscounts(@Param("discounts") float discounts, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Receipt r SET r.iva=:iva WHERE r.id=:id")
    int updateIva(@Param("iva") float iva, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Receipt r SET r.total=:total WHERE r.id=:id")
    int updateTotal(@Param("total") float total, @Param("id") int id);
}
