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

    @OneToOne
    @JoinColumn(name = "idfactura", referencedColumnName = "idfactura")
    private Receipt receipt;

<<<<<<< HEAD
    @OneToMany(targetEntity = PurchaseLine.class, mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
=======
    @OneToMany(targetEntity = PurchaseLine.class, mappedBy = "idPurchase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
>>>>>>> de2083b0446b5b3adee9d9fbdab1e0f162a8cf26
    private List<PurchaseLine> purchaseLines;
}
