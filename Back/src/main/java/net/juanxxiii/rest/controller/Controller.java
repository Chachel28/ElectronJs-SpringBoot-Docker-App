package net.juanxxiii.rest.controller;

import net.juanxxiii.db.entity.Client;
import net.juanxxiii.db.entity.Staff;
import net.juanxxiii.db.entity.Supplier;
import net.juanxxiii.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

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
    public ResponseEntity<Client> newClient(@RequestBody Client newClient) {
        return ResponseEntity.ok(queryService.saveClient(newClient));
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClientList() {
        return ResponseEntity.ok(queryService.getClientList());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> getClient(@PathVariable("id") int id) {
        Client client = queryService.getClient(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> updateClient(@RequestBody Client newClient, @PathVariable("id") int id) {
        int client = queryService.updateClient(newClient, id);
        if (client != -1) {
            return ResponseEntity.ok("Client updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<?> partialUpdateClient(@RequestBody Client client, @PathVariable("id") int id) {
        int value = queryService.partialUpdateClient(client, id);
        if (value != -1) {
            return ResponseEntity.ok("Client partially updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") int id) {
        queryService.deleteClient(id);
        return ResponseEntity.ok("Client deleted");
    }

    //Supplier Mapping
    @PostMapping("/supplier")
    public ResponseEntity<Supplier> newSupplier(@RequestBody Supplier newSupplier) {
        return ResponseEntity.ok(queryService.saveSupplier(newSupplier));
    }

    @GetMapping("/supplier")
    public ResponseEntity<List<Supplier>> getSupplierList() {
        return ResponseEntity.ok(queryService.getSupplierList());
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable("id") int id) {
        Supplier supplier = queryService.getSupplier(id);
        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/supplier/{id}")
    public ResponseEntity<?> updateSupplier(@RequestBody Supplier newSupplier, @PathVariable("id") int id){
        int supplier = queryService.updateSupplier(newSupplier, id);
        if (supplier != -1){
            return ResponseEntity.ok("Supplier updated");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/supplier/{id}")
    public ResponseEntity<?> partialUpdateSupplier(@RequestBody Supplier supplier, @PathVariable("id") int id){
        int value = queryService.partialUpdateSupplier(supplier, id);
        if (value != -1){
            return ResponseEntity.ok("Supplier partially updated");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable("id") int id) {
        queryService.deleteSupplier(id);
        return ResponseEntity.ok("Supplier deleted");
    }


    //Staff Mapping
    @PostMapping("/staff")
    public ResponseEntity<Staff>newStaff(@RequestBody Staff staff) {

        return ResponseEntity.ok(queryService.saveStaff(staff));
    }

    @GetMapping("/staff")
    public List<Staff> getAllStaff() {
        return queryService.getAllStaff();
    }

    @GetMapping("/staff/{id}")
    public Staff getStaff(@PathVariable("id") int id) {
        return queryService.getStaff(id);
    }

    @PutMapping("/staff/{id}")
    public ResponseEntity<?> updateStaff(@RequestBody Staff staff, @PathVariable int id) {
        int value = queryService.updateStaff(staff, id);
        if (value != -1) {
            return ResponseEntity.ok("staff updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") int id) {
        queryService.deleteStaff(id);
        return ResponseEntity.ok("Staff deleted");
    }

}
