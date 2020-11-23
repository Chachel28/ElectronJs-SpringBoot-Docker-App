package net.juanxxiii.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "direcciones_cliente")
public class ClientDirection implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccioncliente")
    private int id;
    @Column(name = "direccion")
    private String direction;
    @Column(name = "idcliente")
    private int client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDirection that = (ClientDirection) o;
        return direction.equals(that.direction);
    }
}
