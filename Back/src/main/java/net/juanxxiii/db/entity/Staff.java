package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "personal")
public class Staff implements Serializable {
    public Staff () {
        this.positionStaff = null;
        this.name = null;
        this.email = null;
        this.password = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersonal")
    private int idStaff;

    @Column(name = "nombrecompleto")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "telefono")
    private int telephone;

    @ManyToOne(targetEntity = PositionStaff.class, optional = false)
    @JoinColumn(name = "idpuesto", referencedColumnName = "idpuesto")
    private PositionStaff positionStaff;
}
