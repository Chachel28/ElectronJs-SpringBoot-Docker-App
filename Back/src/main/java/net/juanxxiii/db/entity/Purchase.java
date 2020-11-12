package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "compras")
public class Purchase implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompra")
    private int id;

    @Column(name = "cantidadcomprada")
    private int quantity;

    @Column(name = "idproveedor")
    private int supplier;
}
