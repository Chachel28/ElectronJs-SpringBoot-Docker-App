package net.juanxxiii.services;

import net.juanxxiii.db.entity.*;
import net.juanxxiii.db.repository.ClientRepository;
import net.juanxxiii.db.repository.StaffRepository;
import net.juanxxiii.db.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class QueryService {


    private final ClientRepository clientRepository;

    private final SupplierRepository supplierRepository;

    private final StaffRepository staffRepository;

    @Autowired
    public QueryService(ClientRepository clientRepository, SupplierRepository supplierRepository, StaffRepository staffRepository) {
        this.clientRepository = clientRepository;
        this.supplierRepository = supplierRepository;
        this.staffRepository = staffRepository;
    }

    //Client queryList
    public Client saveClient(Client newClient) {
        return clientRepository.save(newClient);
    }

    public List<Client> getClientList() {
        return clientRepository.findAll();
    }

    public Client getClient(int id) {
        return clientRepository.findById(id).orElse(null);
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

    public Client partialUpdateClient(Map<String, Object> updates, int id) {
        return clientRepository.findById(id)
                .map(client -> {
                    for(String key : updates.keySet()){
                        switch (key) {
                            case "fullName":
                                client.setFullName((String) updates.get(key));
                                break;
                            case "dni":
                                client.setDni((String) updates.get(key));
                                break;
                            case "iban":
                                client.setIban((String) updates.get(key));
                                break;
                            case "email":
                                client.setEmail((String) updates.get(key));
                                break;
                            case "telephones":
                                client.setTelephones((List<ClientTelephone>) updates.get(key));
                                break;
                            case "directions":
                                client.setDirections((List<ClientDirection>) updates.get(key));
                                break;
                            default:
                                break;
                        }
                    }
                    return clientRepository.save(client);
                })
                .orElse(null);
    }

    public void deleteClient(int id) {
        clientRepository.delete(Objects.requireNonNull(clientRepository.findById(id).orElse(null)));
    }

    //Suplier queryList
    public Supplier getSupplier(int id) {
        return supplierRepository.findById(id).orElse(null);
    }

    //Staff queryList
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaff(int id) {
        return staffRepository.findById(id).orElse(null);
    }
}
