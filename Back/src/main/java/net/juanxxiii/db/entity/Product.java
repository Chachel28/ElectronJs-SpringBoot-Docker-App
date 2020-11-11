package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Product implements Serializable {

    public Product() {
        this.name = null;
        this.description = null;
        this.buyPrice = 0;
        this.sellPrice = 0;
        this.stock = 0;
        this.type = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "preciocompra")
    private float buyPrice;
    @Column(name = "precioventa")
    private float sellPrice;
    @Column(name = "stock")
    private int stock;
    @Column(name = "tipo")
    private String type;
}
