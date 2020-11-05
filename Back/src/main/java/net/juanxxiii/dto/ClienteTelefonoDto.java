package net.juanxxiii.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClienteTelefonoDto {

    private int idCliente;
    private String name;
    private int idTelephone;
    private int numTelephone;

}

