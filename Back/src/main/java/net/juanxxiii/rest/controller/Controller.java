package net.juanxxiii.rest.controller;

import net.juanxxiii.db.entity.Client;
import net.juanxxiii.db.entity.Supplier;
import net.juanxxiii.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    private final QueryService queryService;

    @Autowired
    public Controller(QueryService queryService) {
        this.queryService = queryService;
    }

    //Client Mapping
    @PostMapping("/clients")
    public Client newClient(@RequestBody Client newClient) {
        return queryService.saveClient(newClient);
    }

    @GetMapping("/clients")
    public List<Client> getClientList() {
        return queryService.getClientList();
    }

    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable("id")int id){
        return queryService.getClient(id);
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@RequestBody Client newClient, @PathVariable("id")int id){
        return queryService.updateClient(newClient, id);
    }

//    @PatchMapping("/clients/{id}")
//    public Client partialUpdateClient(@RequestBody Map<String, Object> updates, @PathVariable("id")int id){
//        return queryService.partialUpdateClient(updates, id);
//    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id")int id){
        queryService.deleteClient(id);
        return ResponseEntity.ok("Client deleted");
    }

    //Supplier Mapping
    @GetMapping("/supplier/{id}")
    public Supplier getSupplier(@PathVariable("id") int id) {
        return queryService.getSupplier(id);
    }
}
