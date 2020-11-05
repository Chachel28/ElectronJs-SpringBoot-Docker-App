package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "direccionescliente")
public class ClientDirection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccioncliente")
    private int id;
    @Column(name = "direccion")
    private String direction;
    @Column(name = "idcliente")
    private int client;
}
