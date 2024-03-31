package com.example.sales.system.service;

import com.example.sales.system.domain.dtos.client.ClientView;
import com.example.sales.system.domain.entities.JpaClient;
import com.example.sales.system.exceptionHandling.RecordNotComplete;
import com.example.sales.system.exceptionHandling.RecordNotFoundException;
import com.example.sales.system.mapper.client.ClientMapper;
import com.example.sales.system.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    Logger logger = LoggerFactory.getLogger(ClientService.class);
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper)
    {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }
    public List<ClientView> getAll()
    {
        List<JpaClient> jpaClients = clientRepository.findAll();
        List<ClientView> clientViews = new ArrayList<>();
        //clientViews = jpaClients.stream().map(x -> clientMapper.convertToView(x));
        for (JpaClient jpaClient:jpaClients) {
            clientViews.add(clientMapper.convertToView(jpaClient));
        }
        return clientViews;
    }

    public ClientView findById(Long id)
    {
        Optional<JpaClient> jpaClient = clientRepository.findById(id);
        if(jpaClient.isPresent()) {
            return clientMapper.convertToView(jpaClient.get());
        }else
            throw new RecordNotFoundException("This Record Is Not Found Of Id = "+id);
    }

    public ClientView insert(ClientView clientView)
    {
        try {
            JpaClient JpaClient = clientMapper.convertToModel(clientView);
            clientRepository.save(JpaClient);
            return clientView;
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Inserted");
        }
    }

    public ClientView update(ClientView clientView)
    {
        try {
            Optional<JpaClient> jpaClient = clientRepository.findById(clientView.getId());
            if (jpaClient.isPresent()) {
                JpaClient JpaClient = clientMapper.convertToModel(clientView);
                clientRepository.save(JpaClient);
                return clientView;
            }
            else
                throw new RecordNotFoundException("This Record Is Not Found Of Id = "+clientView.getId());
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Updated");
        }
    }
    public void delete(Long id)
    {
        clientRepository.deleteById(id);
    }

}
