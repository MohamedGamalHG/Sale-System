package com.example.sales.system.mapper.client;

import com.example.sales.system.config.AuditorImplementation;
import com.example.sales.system.domain.dtos.client.ClientView;
import com.example.sales.system.domain.entities.JpaClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClientMapper {
    private ModelMapper modelMapper;
    public ClientMapper(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
        modelMapper.createTypeMap(ClientView.class, JpaClient.class)
                .addMappings(mapper -> {
                    mapper.map(src -> LocalDateTime.now(), JpaClient::setCreated_at);
                    mapper.map(src -> LocalDateTime.now(), JpaClient::setUpdated_at);
                    mapper.map(src -> new AuditorImplementation().getCurrentAuditor(), JpaClient::setCreatedBy);
                    mapper.map(src -> new AuditorImplementation().getCurrentAuditor(), JpaClient::setModifiedBy);
                });

    }
    public ClientView convertToView(JpaClient jpaClient)
    {
        ClientView clientView = modelMapper.map(jpaClient,ClientView.class);
        return clientView;
    }

    public JpaClient convertToModel(ClientView clientView)
    {
        JpaClient jpaClient = modelMapper.map(clientView,JpaClient.class);
        return jpaClient;
    }

}
