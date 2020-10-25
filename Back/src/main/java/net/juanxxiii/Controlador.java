package net.juanxxiii;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Controlador {

    @RequestMapping("/")
    public String index(){
        return "hola";
    }
}
