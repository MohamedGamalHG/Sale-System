package com.example.sales.system.service;

import com.example.sales.system.domain.dtos.product.ProductView;
import com.example.sales.system.domain.entities.JpaProduct;
import com.example.sales.system.exceptionHandling.RecordNotComplete;
import com.example.sales.system.exceptionHandling.RecordNotFoundException;
import com.example.sales.system.mapper.product.ProductMapper;
import com.example.sales.system.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    Logger logger = LoggerFactory.getLogger(ProductService.class);
    public ProductService(ProductRepository productRepository, ProductMapper productMapper)
    {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    public List<ProductView> getAll()
    {
        List<JpaProduct> jpaProducts = productRepository.findAll();
        List<ProductView> productViews = new ArrayList<>();
        //clientViews = jpaClients.stream().map(x -> clientMapper.convertToView(x));
        for (JpaProduct jpaProduct:jpaProducts) {
            productViews.add(productMapper.convertToView(jpaProduct));
        }
        return productViews;
    }

    public ProductView findById(Long id)
    {
        Optional<JpaProduct> jpaProduct = productRepository.findById(id);
        if(jpaProduct.isPresent()) {
            return productMapper.convertToView(jpaProduct.get());
        }else
            throw new RecordNotFoundException("This Record Is Not Found Of Id = "+id);
    }

    public ProductView insert(ProductView productView)
    {
        try {
            JpaProduct jpaProduct = productMapper.convertToModel(productView);
            productRepository.save(jpaProduct);
            return productView;
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Inserted");
        }
    }

    public ProductView update(ProductView productView)
    {
        try {
            Optional<JpaProduct> jpaProduct = productRepository.findById(productView.getId());
            if (jpaProduct.isPresent()) {
                JpaProduct product = productMapper.convertToModel(productView);
                productRepository.save(product);
                return productView;
            }
            else
                throw new RecordNotFoundException("This Record Is Not Found Of Id = "+productView.getId());
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Updated");
        }
    }
    public void delete(Long id)
    {
        try {
            Optional<JpaProduct> jpaProduct = productRepository.findById(id);
            if (jpaProduct.isPresent()) {
                jpaProduct.get().setPrice(0);
                productRepository.save(jpaProduct.get());
                return ;
            }
            else
                throw new RecordNotFoundException("This Record Is Not Found Of Id = "+id);
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Updated");
        }
    }
}
