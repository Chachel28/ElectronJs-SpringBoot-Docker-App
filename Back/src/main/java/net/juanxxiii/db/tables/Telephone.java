package net.juanxxiii.db.tables;

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
    @Column(name = "IdTelefono")
    private int id;
    @Column(name = "numTelefono")
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCliente")
    private Client client;
}
