package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "facturas")
public class Receipt implements Serializable {

    public Receipt() {
        this.discounts = 0;
        this.iva = 0;
        this.receiptDate = null;
        this.subtotal = 0;
        this.total = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idfactura")
    private int id;

    @Column(name = "fechafactura")
    private String receiptDate;

    @Column(name = "descuentos")
    private float discounts;

    @Column(name = "subtotal")
    private float subtotal;

    @Column(name = "iva")
    private float iva;

    @Column(name = "total")
    private float total;
}
