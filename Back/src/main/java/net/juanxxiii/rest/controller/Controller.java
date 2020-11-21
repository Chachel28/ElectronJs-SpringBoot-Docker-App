package net.juanxxiii.rest.controller;

import net.juanxxiii.db.entity.*;
import net.juanxxiii.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/supplier/{id}")
    public Supplier getSupplier(@PathVariable("id") int id) {
        return queryService.getSupplier(id);
    }

    //Staff Mapping
    @PostMapping("/staffs")
    public ResponseEntity<Staff> newStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok(queryService.saveStaff(staff));
    }

    @GetMapping("/staffs")
    public List<Staff> getAllStaff() {
        return queryService.getAllStaff();
    }

    @GetMapping("/staffs/{id}")
    public Staff getStaff(@PathVariable("id") int id) {
        return queryService.getStaff(id);
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<?> updateStaff(@RequestBody Staff staff, @PathVariable int id) {
        int value = queryService.updateStaff(staff, id);
        if (value != -1) {
            return ResponseEntity.ok("staff updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/staffs/{id}")
    public ResponseEntity<?> partialUpdateStaff(@RequestBody Staff staff, @PathVariable int id) {
        int value = queryService.partialUpdateStaff(staff, id);
        if (value != -1) {
            return ResponseEntity.ok("staff updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/staffs/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") int id) {
        queryService.deleteStaff(id);
        return ResponseEntity.ok("Staff deleted");
    }

    //Product Mapping
    @PostMapping("/products")
    public ResponseEntity<?> newProduct(@RequestBody Product newProduct) {
        Product product = queryService.saveProduct(newProduct);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductList() {
        return ResponseEntity.ok(queryService.getProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") int id) {
        Product product = queryService.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lastproducts")
    public ResponseEntity<List<Product>> getTwentyLastProducts() {
        return ResponseEntity.ok(queryService.getLastProducts());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable int id) {
        int value = queryService.updateProduct(product, id);
        if (value != -1) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<?> partialUpdateProduct(@RequestBody Product product, @PathVariable int id) {
        int value = queryService.partialUpdateProduct(product, id);
        if (value != -1) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
        queryService.deleteProduct(id);
        return ResponseEntity.noContent().build();

    }

    //Receipt Mapping
    @PostMapping("/receipts")
    public ResponseEntity<?> newReceipt(@RequestBody Receipt newReceipt) {
        Receipt receipt = queryService.saveReceipt(newReceipt);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/receipts")
    public ResponseEntity<List<Receipt>> getReceiptList() {
        return ResponseEntity.ok(queryService.getReceipts());
    }

    @GetMapping("/receipts/{id}")
    public ResponseEntity<?> getReceipt(@PathVariable("id") int id) {
        Receipt receipt = queryService.getReceipt(id);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/receipts/{id}")
    public ResponseEntity<?> updateReceipt(@RequestBody Receipt receipt, @PathVariable("id") int id) {
        int value = queryService.updateReceipt(receipt, id);
        if (value != -1) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/receipts/{id}")
    public ResponseEntity<?> partialUpdateReceipt(@RequestBody Receipt receipt, @PathVariable("id") int id) {
        int value = queryService.partialUpdateReceipt(receipt, id);
        if (value != -1) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/receipts/{id}")
    public ResponseEntity<?> deleteReceipt(@PathVariable("id") int id) {
        queryService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }

    //Sale Mapping
    @PostMapping("/sales")
    public ResponseEntity<?> newSale(@RequestBody Sale newSale) {
        Sale sale = queryService.saveSale(newSale);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getSaleList() {
        return ResponseEntity.ok(queryService.getSaleList());
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<?> getSale(@PathVariable("id") int id) {
        Sale sale = queryService.getSale(id);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/sales/{id}")
    public ResponseEntity<?> updateSale(@RequestBody Sale newSale, @PathVariable("id") int id) {
        int sale = queryService.updateSale(newSale, id);
        if (sale != -1) {
            return ResponseEntity.ok("Sale updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/sales/{id}")
    public ResponseEntity<?> updatePartialSale(@RequestBody Sale newSale, @PathVariable("id") int id) {
        int sale = queryService.partialUpdateSale(newSale, id);
        if (sale != -1) {
            return ResponseEntity.ok("Sale updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/sales/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable("id") int id) {
        queryService.deleteSale(id);
        return ResponseEntity.ok("Sale deleted");
    }

    //Purchase Mapping
    @PostMapping("/purchases")
    public ResponseEntity<?> newPurchase(@RequestBody Purchase newPurchase) {
        Purchase purchase = queryService.savePurchase(newPurchase);
        if (purchase != null) {
            return ResponseEntity.ok(purchase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<Purchase>> getPurchaseList() {
        return ResponseEntity.ok(queryService.getPurchaseList());
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<?> getPurchases(@PathVariable("id") int id) {
        Purchase purchase = queryService.getPurchase(id);
        if (purchase != null) {
            return ResponseEntity.ok(purchase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/purchases/{id}")
    public ResponseEntity<?> updatePurchase(@RequestBody Purchase newPurchase, @PathVariable("id") int id) {
        int purchase = queryService.updatePurchase(newPurchase, id);
        if (purchase != -1) {
            return ResponseEntity.ok("Purchase updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/purchases/{id}")
    public ResponseEntity<?> updatePartialPurchase(@RequestBody Purchase newPurchase, @PathVariable("id") int id) {
        int purchase = queryService.partialUpdatePurchase(newPurchase, id);
        if (purchase != -1) {
            return ResponseEntity.ok("Purchase updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable("id") int id) {
        queryService.deletePurchase(id);
        return ResponseEntity.ok("Purchase deleted");
    }

}
