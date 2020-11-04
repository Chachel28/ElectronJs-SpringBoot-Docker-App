package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private int id;
    @Column(name = "nombrecompleto")
    private String fullName;
    @Column(name = "dnicif")
    private String dni;
    @Column(name = "iban")
    private String iban;
    @Column(name = "email")
    private String email;

    @OneToMany(targetEntity = Telephone.class, mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private List<Telephone> telephones;
}
