package net.juanxxiii.rest.controller;

import net.juanxxiii.db.entity.Client;
import net.juanxxiii.db.entity.Suplier;
import net.juanxxiii.dto.ClienteCompletoDto;
import net.juanxxiii.dto.ClienteTelefonoDto;
import net.juanxxiii.services.JoinQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    private JoinQueryService joinQueryService;

    @GetMapping("/clients")
    public ResponseEntity<List<ClienteTelefonoDto>> getDtoList() {
        return new ResponseEntity<>(joinQueryService.getDtoList(), HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteCompletoDto>> getFullClientDtoList(){
        return new ResponseEntity<>(joinQueryService.getFullClientDtoList(), HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable("id")int id){
        return joinQueryService.getClient(id);
    }

    @GetMapping("/suplier/{id}")
    public Suplier getSuplier(@PathVariable("id") int id) {
        return joinQueryService.getSuplier(id);
    }
}
