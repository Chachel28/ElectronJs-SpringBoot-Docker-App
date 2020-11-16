package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table("lineas_compra")
public class PurchaseLine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlineacompra")
    private int id;

    @Column(name = "idcompra")
    private int idPurchase;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    private Product idProduct;

    @Column(name = "cantidadcomprada")
    private int quantity;
}
