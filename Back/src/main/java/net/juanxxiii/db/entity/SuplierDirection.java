package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "direccionesproveedor")
public class SuplierDirection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccionproveedor")
    private int id;
    @Column(name = "direccion")
    private String direction;
    @Column(name = "idproveedor")
    private int suplier;
}
