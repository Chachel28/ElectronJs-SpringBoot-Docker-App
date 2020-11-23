package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "compras")
public class Purchase implements Serializable{
    public Purchase() {
        this.supplier=0;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompra")
    private int id;

    @Column(name = "idproveedor")
    private int supplier;

    @org.springframework.data.annotation.Transient
    @ManyToOne(targetEntity = Staff.class, optional = false)
    @JoinColumn(name = "idpersonal", referencedColumnName = "idpersonal")
    private Staff staff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idfactura", referencedColumnName = "idfactura")
    private Receipt receipt;

    @OneToMany(targetEntity = PurchaseLine.class, mappedBy = "idPurchase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchaseLine> purchaseLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return supplier == purchase.supplier &&
                staff.equals(purchase.staff) &&
                receipt.equals(purchase.receipt);
    }

}
