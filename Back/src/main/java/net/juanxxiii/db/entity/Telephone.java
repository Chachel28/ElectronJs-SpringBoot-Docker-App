package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "telefonoscliente")
public class Telephone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtelefono")
    private int id;
    @Column(name = "numtelefono")
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    private Client client;
}
