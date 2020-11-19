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

    @OneToMany(targetEntity = SaleLine.class, mappedBy = "idSale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleLine> saleLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return client == sale.client &&
                staff.equals(sale.staff) &&
                receipt.equals(sale.receipt);
    }
}