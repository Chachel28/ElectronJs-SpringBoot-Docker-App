package net.juanxxiii;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Controlador {

    @RequestMapping("/clientes/{id}")
    public String index(@PathVariable("id") String id){
        return id;
    }

    @RequestMapping("/clientes")
    public String index2(){
        return "Index2";
    }
}
