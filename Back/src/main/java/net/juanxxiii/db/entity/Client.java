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

    public Client() {
        this.fullName = null;
        this.dni = null;
        this.iban = null;
        this.email = null;
        this.telephones = null;
        this.directions = null;
        this.sales = null;
    }

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

    @OneToMany(targetEntity = ClientTelephone.class, mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClientTelephone> telephones;

    @OneToMany(targetEntity = ClientDirection.class, mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClientDirection> directions;

    @OneToMany(targetEntity = Sale.class, mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sale> sales;
}
