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

    @ManyToOne(targetEntity = Staff.class, optional = false)
    @JoinColumn(name = "idpersonal", referencedColumnName = "idpersonal")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "idfactura", referencedColumnName = "idfactura")
    private Receipt receipt;

    @OneToMany(targetEntity = PurchaseLine.class, mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchaseLine> purchaseLines;
}
