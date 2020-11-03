package net.juanxxiii.db.tables;

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
    @Column(name = "idCliente")
    private int id;
    @Column(name = "nombreCompleto")
    private String fullName;
    @Column(name = "dniCif")
    private String dni;
    @Column(name = "Iban")
    private String iban;
    @Column(name = "email")
    private String email;

    @OneToMany(targetEntity = Telephone.class, mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Telephone> telephones;
}
