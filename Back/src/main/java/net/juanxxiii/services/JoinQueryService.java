package net.juanxxiii.services;

import net.juanxxiii.db.repos.ClientRepository;
import net.juanxxiii.dto.TelephoneDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JoinQueryService {

    @Resource
    private ClientRepository clientRepository;

    public List<TelephoneDto> getDtoList(){
        return clientRepository.fetchTelephoneInnerJoin();
    }
}
