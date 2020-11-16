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

    @ManyToOne(targetEntity = Staff.class, optional = false)
    @JoinColumn(name = "idpersonal", referencedColumnName = "idpersonal")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "idfactura", referencedColumnName = "idfactura")
    private Receipt receipt;

    @OneToMany(targetEntity = SaleLine.class, mappedBy = "idsale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleLine> saleLines;

}