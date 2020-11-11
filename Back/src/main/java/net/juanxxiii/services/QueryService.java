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
    private final PositionStaffRepository positionStaffRepository;

    @Autowired
    public QueryService(ClientRepository clientRepository,
                        SupplierRepository supplierRepository,
                        StaffRepository staffRepository,
                        ClientTelephoneRepository clientTelephoneRepository,
                        ClientDirectionRepository clientDirectionRepository,
                        PositionStaffRepository positionStaffRepository) {
        this.clientRepository = clientRepository;
        this.supplierRepository = supplierRepository;
        this.staffRepository = staffRepository;
        this.clientTelephoneRepository = clientTelephoneRepository;
        this.clientDirectionRepository = clientDirectionRepository;
        this.positionStaffRepository = positionStaffRepository;
    }

    //Client queryList
    public Client saveClient(Client newClient) {
        List<ClientTelephone> telephones = null;
        List<ClientDirection> directions = null;
        if (!newClient.getTelephones().isEmpty()) {
            telephones = newClient.getTelephones();
            newClient.setTelephones(null);
        }
        if (!newClient.getDirections().isEmpty()) {
            directions = newClient.getDirections();
            newClient.setDirections(null);
        }
        clientRepository.save(newClient);
        int id = clientRepository.lastId();
        if (telephones != null) {
            telephones.forEach(telephone -> {
                telephone.setClient(id);
                clientTelephoneRepository.save(telephone);
            });
        }
        if (directions != null) {
            directions.forEach(direction -> {
                direction.setClient(id);
                clientDirectionRepository.save(direction);
            });
        }
        return clientRepository.findById(id).orElse(null);
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
                                    telephone.setClient(client.getId());
                                    clientTelephoneRepository.save(telephone);
                                }
                            });
                    telephones.forEach(telephone ->{
                        if (!newClient.getTelephones().contains(telephone)) {
                            clientTelephoneRepository.deleteById(telephone.getId());
                        }
                    });
                    newClient.getDirections()
                            .forEach(direction -> {
                                if (!directions.contains(direction)) {
                                    direction.setClient(client.getId());
                                    clientDirectionRepository.save(direction);
                                }
                            });
                    directions.forEach(direction ->{
                        if (!newClient.getDirections().contains(direction)) {
                            clientDirectionRepository.deleteById(direction.getId());
                        }
                    });
                    return clientRepository.updateClient(newClient.getFullName(), newClient.getDni(), newClient.getEmail(), newClient.getIban(), newClient.getId());
                })
                .orElse(-1);
    }

    public int partialUpdateClient(Client newClient, int id) {
        return clientRepository.findById(id)
                .map(client -> {
                    if (newClient.getFullName() != null) {
                        clientRepository.updateClientName(newClient.getFullName(), id);
                    }
                    if (newClient.getDni() != null) {
                        clientRepository.updateClientDni(newClient.getDni(), id);
                    }
                    if (newClient.getIban() != null) {
                        clientRepository.updateClientIban(newClient.getIban(), id);
                    }
                    if (newClient.getEmail() != null) {
                        clientRepository.updateClientEmail(newClient.getEmail(), id);
                    }
                    if (newClient.getTelephones() != null) {
                        newClient.getTelephones().forEach(clientTelephone -> {
                            if (!client.getTelephones().contains(clientTelephone)) {
                                clientTelephone.setClient(client.getId());
                                clientTelephoneRepository.save(clientTelephone);
                            }
                        });
                        client.getTelephones().forEach(telephone ->{
                            if (!newClient.getTelephones().contains(telephone)) {
                                clientTelephoneRepository.deleteById(telephone.getId());
                            }
                        });
                    }
                    if (newClient.getDirections() != null) {
                        newClient.getDirections().forEach(clientDirection -> {
                            if (!client.getDirections().contains(clientDirection)) {
                                clientDirection.setClient(client.getId());
                                clientDirectionRepository.save(clientDirection);
                            }
                        });
                        client.getDirections().forEach(direction ->{
                            if (!newClient.getDirections().contains(direction)) {
                                clientDirectionRepository.deleteById(direction.getId());
                            }
                        });
                    }
                    return 1;
                })
                .orElse(-1);
    }

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
    public Staff saveStaff(Staff staff) {
        PositionStaff positionStaff = staff.getPositionStaff();
        PositionStaff pSRepo = positionStaffRepository.findByName(positionStaff.getName()).orElse(null);
        if (pSRepo == null) {
            positionStaffRepository.save(positionStaff);
        } else {
            staff.setPositionStaff(pSRepo);
        }
        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository
                .findAll();
    }

    public Staff getStaff(int id) {
        return staffRepository
                .findById(id)
                .orElse(null);
    }

    public int updateStaff(Staff staff, int id) {
        return staffRepository.findById(id).map(s -> {
            positionStaffRepository.findById(staff.getPositionStaff().getIdPositionStaff()).orElse(positionStaffRepository.save(staff.getPositionStaff()));
            return staffRepository.updateStaff(staff.getName(), staff.getEmail(), staff.getPassword(), id);
        }).orElse(-1);
    }

    public void deleteStaff(int id) {
        staffRepository.delete(Objects.requireNonNull(staffRepository.findById(id).orElse(null)));
    }

}
