package net.juanxxiii.services;

import net.juanxxiii.db.entity.*;
import net.juanxxiii.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class QueryService {


    private final ClientRepository clientRepository;
    private final SupplierRepository supplierRepository;
    private final StaffRepository staffRepository;
    private final ClientTelephoneRepository clientTelephoneRepository;
    private final ClientDirectionRepository clientDirectionRepository;

    @Autowired
    public QueryService(ClientRepository clientRepository,
                        SupplierRepository supplierRepository,
                        StaffRepository staffRepository,
                        ClientTelephoneRepository clientTelephoneRepository,
                        ClientDirectionRepository clientDirectionRepository) {

        this.clientRepository = clientRepository;
        this.supplierRepository = supplierRepository;
        this.staffRepository = staffRepository;
        this.clientTelephoneRepository = clientTelephoneRepository;
        this.clientDirectionRepository = clientDirectionRepository;
    }

    //Client queryList
    public Client saveClient(Client newClient) {
        return clientRepository
                .save(newClient);
    }

    public List<Client> getClientList() {
        return clientRepository
                .findAll();
    }

    public Client getClient(int id) {
        return clientRepository
                .findById(id)
                .orElse(null);
    }

    public int updateClient(Client newClient, int id) {
        return clientRepository.findById(id)
                .map(client -> {
                    List<ClientTelephone> telephones = client.getTelephones();
                    List<ClientDirection> directions = client.getDirections();
                    newClient.getTelephones()
                            .forEach(telephone -> {
                                if (!telephones.contains(telephone)) {
                                    clientTelephoneRepository.save(telephone);
                                }
                            });
                    newClient.getDirections()
                            .forEach(direction -> {
                                if (!directions.contains(direction)) {
                                    clientDirectionRepository.save(direction);
                                }
                            });
                    return clientRepository.updateClient(newClient.getFullName(), newClient.getDni(), newClient.getEmail(), newClient.getIban(), newClient.getId());
                })
                .orElse(-1);
    }

//    public Client partialUpdateClient(Map<String, Object> updates, int id) {
//        return clientRepository.findById(id)
//                .map(client -> {
//                    for (String key : updates.keySet()) {
//                        switch (key) {
//                            case "fullName":
//                                client.setFullName((String) updates.get(key));
//                                break;
//                            case "dni":
//                                client.setDni((String) updates.get(key));
//                                break;
//                            case "iban":
//                                client.setIban((String) updates.get(key));
//                                break;
//                            case "email":
//                                client.setEmail((String) updates.get(key));
//                                break;
//                            case "telephones":
//                                List<ClientTelephone> telephones = client.getTelephones();
//                                (List<ClientTelephone>)updates.get(key).forEach(telephone ->{
//                                        if (!telephones.contains(telephone)) {
//                                            clientTelephoneRepository.save(telephone);
//                                        }
//                                    });
//                                break;
//                            case "directions":
//                                client.setDirections((List<?>) updates.get(key));
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                    return clientRepository.save(client);
//                })
//                .orElse(null);
//    }

    public void deleteClient(int id) {
        clientRepository
                .delete(Objects
                        .requireNonNull(clientRepository
                                .findById(id)
                                .orElse(null)));
    }

    //Supplier queryList
    public Supplier getSupplier(int id) {
        return supplierRepository
                .findById(id)
                .orElse(null);
    }

    //Staff queryList
    public List<Staff> getAllStaff() {
        return staffRepository
                .findAll();
    }

    public Staff getStaff(int id) {
        return staffRepository
                .findById(id)
                .orElse(null);
    }
}
