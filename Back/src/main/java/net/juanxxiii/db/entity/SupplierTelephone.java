package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "telefonos_proveedor")
public class SupplierTelephone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtelefonoprov")
    private int id;
    @Column(name = "numtelefono")
    private int number;
    @Column(name = "idproveedor")
    private int supplier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierTelephone that = (SupplierTelephone) o;
        return number == that.number;
    }
}
