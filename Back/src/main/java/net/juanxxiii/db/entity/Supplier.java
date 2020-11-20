package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "proveedores")
public class Supplier implements Serializable {

    public Supplier() {
        this.fullName = null;
        this.dni = null;
        this.email = null;
        this.telephones = null;
        this.directions = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproveedor")
    private int id;
    @Column(name = "nombre")
    private String fullName;
    @Column(name = "dnicif")
    private String dni;
    @Column(name = "email")
    private String email;

    @OneToMany(targetEntity = SupplierTelephone.class, mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SupplierTelephone> telephones;

    @OneToMany(targetEntity = SupplierDirection.class, mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SupplierDirection> directions;

    @OneToMany(targetEntity = Purchase.class, mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchases;
}
