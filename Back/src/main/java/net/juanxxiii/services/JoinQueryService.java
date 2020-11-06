package net.juanxxiii.services;

import net.juanxxiii.db.entity.Client;
import net.juanxxiii.db.entity.Suplier;
import net.juanxxiii.db.entity.Staff;
import net.juanxxiii.db.repository.ClientRepository;
import net.juanxxiii.db.repository.SuplierRepository;
import net.juanxxiii.db.repository.StaffRepository;
import net.juanxxiii.dto.ClienteCompletoDto;
import net.juanxxiii.dto.ClienteTelefonoDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JoinQueryService {

    @Resource
    private ClientRepository clientRepository;

    @Resource
    private SuplierRepository suplierRepository;

    public Client getClient(int id){
    @Resource
    private StaffRepository staffRepository;

    public Client getClient(int id) {
        return clientRepository.findById(id).get();
    }

    public Suplier getSuplier(int id) {
        return suplierRepository.findById(id).get();
    }

    public List<ClienteTelefonoDto> getDtoList(){
        return clientRepository.fetchTelephoneInnerJoin();
    }

    public List<ClienteCompletoDto> getFullClientDtoList() {
        return clientRepository.fetchFullClientJoin();
    }

    public Staff getStaff(int id) {
        return staffRepository.findById(id).get();
    }
}
