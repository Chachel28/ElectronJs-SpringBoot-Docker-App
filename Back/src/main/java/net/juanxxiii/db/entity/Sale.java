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
    public Sale() {
        this.client = 0;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idventa")
    private int id;

    @Column(name = "idcliente")
    private int client;

    @org.springframework.data.annotation.Transient
    @ManyToOne(targetEntity = Staff.class, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idpersonal", referencedColumnName = "idpersonal")
    private Staff staff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idfactura", referencedColumnName = "idfactura")
    private Receipt receipt;

<<<<<<< HEAD
    @OneToMany(targetEntity = SaleLine.class, mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
=======
    @OneToMany(targetEntity = SaleLine.class, mappedBy = "idSale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
>>>>>>> de2083b0446b5b3adee9d9fbdab1e0f162a8cf26
    private List<SaleLine> saleLines;

}