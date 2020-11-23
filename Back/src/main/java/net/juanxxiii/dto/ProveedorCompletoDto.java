package net.juanxxiii.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProveedorCompletoDto {

    private int idProveedor;
    private String name;
    private int idDirection;
    private String direction;
    private int idTelephone;
    private int telephone;
}
