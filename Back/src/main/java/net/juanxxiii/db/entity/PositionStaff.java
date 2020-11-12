package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "puestos")
public class PositionStaff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpuesto")
    private int idPositionStaff;

    @Column(name = "nombrepuesto")
    private String name;

    @Column(name = "seccion")
    private String section;

    @Column(name = "privilegio")
    private int privilege;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionStaff that = (PositionStaff) o;
        return privilege == that.privilege &&
                name.equals(that.name) &&
                section.equals(that.section);
    }
}
