package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "puestos")
public class PositionStaff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersonal")
    private int idPositionStaff;

    @Column(name = "nombrepuesto")
    private String nombre;

    @Column(name = "seccion")
    private String section;

    @Column(name = "privilegio")
    private int privilege;

}
