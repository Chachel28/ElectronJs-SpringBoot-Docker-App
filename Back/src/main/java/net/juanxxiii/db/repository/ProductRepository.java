package net.juanxxiii.db.repository;

import net.juanxxiii.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.name=:name, p.description=:description, p.buyPrice=:buyPrice, p.sellPrice=:sellPrice, p.type=:type, p.stock=:stock WHERE p.id=:id")
    int updateProduct(@Param("name") String name, @Param("description") String description, @Param("buyPrice") float buyPrice, @Param("sellPrice") float sellPrice, @Param("type") String type, @Param("stock") int stock,@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.name=:name WHERE p.id=:id")
    int updateProductName(@Param("name")String name, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.description=:description WHERE p.id=:id")
    int updateProductDescription(@Param("description")String description, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.buyPrice=:buyPrice WHERE p.id=:id")
    int updateProductBuyPrice(@Param("buyPrice")float buyPrice, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.sellPrice=:sellPrice WHERE p.id=:id")
    int updateProductSellPrice(@Param("sellPrice")float sellPrice, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.type=:type WHERE p.id=:id")
    int updateProductType(@Param("type")String type, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.stock=:stock WHERE p.id=:id")
    int updateProductStock(@Param("stock")int stock, @Param("id") int id);
}
