package com.example.sales.system.service;

import com.example.sales.system.domain.dtos.client.ClientView;
import com.example.sales.system.domain.dtos.sale.SaleUpdate;
import com.example.sales.system.domain.dtos.sale.SaleView;
import com.example.sales.system.domain.entities.JpaClient;
import com.example.sales.system.domain.entities.JpaSale;
import com.example.sales.system.exceptionHandling.RecordNotComplete;
import com.example.sales.system.exceptionHandling.RecordNotFoundException;
import com.example.sales.system.mapper.sale.SaleMapper;
import com.example.sales.system.repository.ProductRepository;
import com.example.sales.system.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    Logger logger = LoggerFactory.getLogger(SaleService.class);
    public SaleService(SaleRepository saleRepository, SaleMapper saleMapper)
    {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }
    public List<SaleView> getAll()
    {
        List<JpaSale> jpaSales = saleRepository.findAll();
        List<SaleView> saleViews = new ArrayList<>();
        //clientViews = jpaClients.stream().map(x -> clientMapper.convertToView(x));
        for (JpaSale jpaSale:jpaSales) {
            saleViews.add(saleMapper.convertToView(jpaSale));
        }
        return saleViews;
    }

    public SaleView findById(Long id)
    {
        Optional<JpaSale> jpaSale = saleRepository.findById(id);
        if(jpaSale.isPresent()) {
            return saleMapper.convertToView(jpaSale.get());
        }else
            throw new RecordNotFoundException("This Record Is Not Found Of Id = "+id);
    }

    public JpaSale insert(JpaSale JpaSale)
    {
        try {
            JpaSale JpaSaleInserted = saleRepository.save(JpaSale);
            return JpaSaleInserted;
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Inserted");
        }
    }

    public JpaSale update(SaleUpdate saleUpdate)
    {
        try {
            Optional<JpaSale> jpaSale = saleRepository.findById(saleUpdate.getId());
            if (jpaSale.isPresent()) {
                jpaSale.get().setPrice(saleUpdate.getPrice());
                jpaSale.get().setQuantity(saleUpdate.getQuantity());
                jpaSale.get().setId(saleUpdate.getId());
                return  saleRepository.save(jpaSale.get());
            }
            else
                throw new RecordNotFoundException("This Record Is Not Found Of Id = "+saleUpdate.getId());
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Updated");
        }
    }
    public void delete(Long id)
    {
        saleRepository.deleteById(id);
    }
}
