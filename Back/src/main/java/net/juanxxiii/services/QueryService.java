package net.juanxxiii.services;

import net.juanxxiii.db.entity.Client;
import net.juanxxiii.db.entity.Supplier;
import net.juanxxiii.db.repository.ClientRepository;
import net.juanxxiii.db.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class QueryService {


    private final ClientRepository clientRepository;

    private final SupplierRepository supplierRepository;

    @Autowired
    public QueryService(ClientRepository clientRepository, SupplierRepository supplierRepository) {
        this.clientRepository = clientRepository;
        this.supplierRepository = supplierRepository;
    }

    //Client queryList
    public Client saveClient(Client newClient) {
        return clientRepository.save(newClient);
    }

    public List<Client> getClientList() {
        return clientRepository.findAll();
    }

    public Client getClient(int id) {
        return clientRepository.findById(id).get();
    }

    public Client updateClient(Client newClient, int id) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setFullName(newClient.getFullName());
                    client.setDni(newClient.getDni());
                    client.setEmail(newClient.getEmail());
                    client.setIban(newClient.getIban());
                    client.setDirections(newClient.getDirections());
                    client.setTelephones(newClient.getTelephones());
                    return clientRepository.save(client);
                })
                .orElse(null);
    }

//    public Client partialUpdateClient(Map<String, Object> updates, int id) {
//        return clientRepository.findById(id)
//                .map(client -> {
//                    client.setFullName(newClient.getFullName());
//                    client.setDni(newClient.getDni());
//                    client.setEmail(newClient.getEmail());
//                    client.setIban(newClient.getIban());
//                    client.setDirections(newClient.getDirections());
//                    client.setTelephones(newClient.getTelephones());
//                    return clientRepository.save(client);
//                })
//                .orElseGet(() -> clientRepository.save(newClient));
//    }

    public void deleteClient(int id) {
        clientRepository.delete(clientRepository.findById(id).get());
    }
    //Suplier queryList

    public Supplier getSupplier(int id) {
        return supplierRepository.findById(id).get();
    }
}