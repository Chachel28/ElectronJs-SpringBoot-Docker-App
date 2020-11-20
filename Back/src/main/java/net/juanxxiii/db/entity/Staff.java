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
        this.telephone = 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return telephone == staff.telephone &&
                name.equals(staff.name) &&
                email.equals(staff.email) &&
                password.equals(staff.password) &&
                positionStaff.equals(staff.positionStaff);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "idStaff=" + idStaff +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telephone=" + telephone +
                ", positionStaff=" + positionStaff +
                '}';
    }
}
