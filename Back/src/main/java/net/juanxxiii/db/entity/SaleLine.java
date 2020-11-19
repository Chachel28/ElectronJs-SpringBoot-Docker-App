package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "lineas_venta")
public class SaleLine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlineaventa")
    private int id;

    @Column(name = "idventa")
    private int idSale;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    private Product idProduct;

    @Column(name = "cantidadvendida")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleLine saleLine = (SaleLine) o;
        return quantity == saleLine.quantity &&
                idProduct.equals(saleLine.idProduct);
    }
}
