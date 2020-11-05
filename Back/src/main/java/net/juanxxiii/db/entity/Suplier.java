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
public class Suplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproveedor")
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "dnicif")
    private String dni;
    @Column(name = "email")
    private String email;

    @OneToMany(targetEntity = SuplierTelephone.class, mappedBy = "suplier", fetch = FetchType.LAZY)
    private List<SuplierTelephone> telephones;
    @OneToMany(targetEntity = SuplierDirection.class, mappedBy = "suplier", fetch = FetchType.LAZY)
    private List<SuplierDirection> directions;
}
