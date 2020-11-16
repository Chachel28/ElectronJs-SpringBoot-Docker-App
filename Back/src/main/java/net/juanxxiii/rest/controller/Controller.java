package net.juanxxiii.rest.controller;

import net.juanxxiii.db.entity.*;
import net.juanxxiii.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity<?> newClient(@RequestBody Client newClient) {
        Client client = queryService.saveClient(newClient);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @PatchMapping("/staff/{id}")
    public ResponseEntity<?> partialUpdateStaff(@RequestBody Staff staff, @PathVariable int id) {
        int value = queryService.partialUpdateStaff(staff, id);
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

    //Product Mapping
    @PostMapping("/product")
    public ResponseEntity<?> newProduct(@RequestBody Product newProduct) {
        Product product = queryService.saveProduct(newProduct);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductList() {
        return ResponseEntity.ok(queryService.getProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") int id) {
        Product product = queryService.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable int id) {
        int value = queryService.updateProduct(product, id);
        if (value != -1) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<?> partialUpdateProduct(@RequestBody Product product, @PathVariable int id) {
        int value = queryService.partialUpdateProduct(product, id);
        if (value != -1) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
        queryService.deleteProduct(id);
        return ResponseEntity.noContent().build();

    }

    //Receipt Mapping
    @PostMapping("/receipt")
    public ResponseEntity<?> newReceipt(@RequestBody Receipt newReceipt) {
        Receipt receipt = queryService.saveReceipt(newReceipt);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/receipt")
    public ResponseEntity<List<Receipt>> getReceiptList() {
        return ResponseEntity.ok(queryService.getReceipts());
    }

    @GetMapping("/receipt/{id}")
    public ResponseEntity<?> getReceipt(@PathVariable("id") int id) {
        Receipt receipt = queryService.getReceipt(id);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateReceipt(@RequestBody Receipt receipt, @PathVariable("id") int id) {
        int value = queryService.updateReceipt(receipt, id);
        if (value != -1) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/receipt/{id}")
    public ResponseEntity<?> partialUpdateReceipt(@RequestBody Receipt receipt, @PathVariable("id") int id) {
        int value = queryService.partialUpdateReceipt(receipt, id);
        if (value != -1) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/receipt/{id}")
    public ResponseEntity<?> deleteReceipt(@PathVariable("id") int id){
        queryService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }


}
