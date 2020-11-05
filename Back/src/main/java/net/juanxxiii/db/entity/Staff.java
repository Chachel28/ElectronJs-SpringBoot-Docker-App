package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Table(name = "personal")
public class Staff implements Serializable {
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

    @OneToOne (targetEntity = PositionStaff.class, mappedBy = "idpositionstaff", fetch = FetchType.LAZY)
    private PositionStaff positionStaff;
}
