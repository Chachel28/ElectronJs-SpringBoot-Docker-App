package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "telefonosproveedor")
public class SuplierTelephone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtelefonoprov")
    private int id;
    @Column(name = "numtelefono")
    private int number;
    @Column(name = "idproveedor")
    private int suplier;
}
