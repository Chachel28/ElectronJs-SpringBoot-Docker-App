package net.juanxxiii.services;

import net.juanxxiii.db.entity.Client;
import net.juanxxiii.db.repository.ClientRepository;
import net.juanxxiii.dto.ClienteCompletoDto;
import net.juanxxiii.dto.ClienteTelefonoDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JoinQueryService {

    @Resource
    private ClientRepository clientRepository;

    public Client getClient(int id){
        return clientRepository.findById(id).get();
    }

    public List<ClienteTelefonoDto> getDtoList(){
        return clientRepository.fetchTelephoneInnerJoin();
    }

    public List<ClienteCompletoDto> getFullClientDtoList(){
        return clientRepository.fetchFullClientJoin();
    }
}
