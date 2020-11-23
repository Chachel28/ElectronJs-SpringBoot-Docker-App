package net.juanxxiii.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProveedorTelefonoDto {

    private int idProveedor;
    private String name;
    private int idTelephone;
    private int numTelephone;
}
