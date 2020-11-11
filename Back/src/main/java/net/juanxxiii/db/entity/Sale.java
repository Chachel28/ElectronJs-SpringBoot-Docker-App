package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ventas")
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idventa")
    private int id;

    @Column(name = "cantidadvendida")
    private int quantity;

    @Column(name = "idcliente")
    private int client;

    @OneToMany(targetEntity = Product.class, mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    //Factura
}
