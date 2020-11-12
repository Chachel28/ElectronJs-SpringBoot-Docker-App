package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "direccionesproveedor")
public class SupplierDirection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccionproveedor")
    private int id;
    @Column(name = "direccion")
    private String direction;
    @Column(name = "idproveedor")
    private int supplier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierDirection that = (SupplierDirection) o;
        return direction.equals(that.direction);
    }
}
