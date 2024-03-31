package com.example.sales.system.mapper.product;

import com.example.sales.system.config.AuditorImplementation;
import com.example.sales.system.domain.dtos.client.ClientView;
import com.example.sales.system.domain.dtos.product.ProductView;
import com.example.sales.system.domain.entities.JpaClient;
import com.example.sales.system.domain.entities.JpaProduct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {
    private ModelMapper modelMapper;
    public ProductMapper(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
        modelMapper.createTypeMap(ProductView.class, JpaProduct.class)
                .addMappings(mapper -> {
                    mapper.map(src -> LocalDateTime.now(), JpaProduct::setCreated_at);
                    mapper.map(src -> LocalDateTime.now(), JpaProduct::setUpdated_at);
                    mapper.map(src -> new AuditorImplementation().getCurrentAuditor(), JpaProduct::setCreatedBy);
                    mapper.map(src -> new AuditorImplementation().getCurrentAuditor(), JpaProduct::setModifiedBy);
                });

    }
    public ProductView convertToView(JpaProduct jpaProduct)
    {
        ProductView productView = modelMapper.map(jpaProduct,ProductView.class);
        return productView;
    }

    public JpaProduct convertToModel(ProductView productView)
    {
        JpaProduct jpaProduct = modelMapper.map(productView,JpaProduct.class);
        return jpaProduct;
    }
}
