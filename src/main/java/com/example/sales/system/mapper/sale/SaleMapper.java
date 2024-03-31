package com.example.sales.system.mapper.sale;

import com.example.sales.system.domain.dtos.sale.SaleView;
import com.example.sales.system.domain.entities.JpaSale;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {
    private ModelMapper modelMapper;
    public SaleMapper(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
//        modelMapper.createTypeMap(SaleView.class, JpaSale.class)
//                .addMappings(mapper -> {
//                    mapper.map(src -> LocalDateTime.now(), JpaSale::setCreated_at);
//                    mapper.map(src -> LocalDateTime.now(), JpaSale::setUpdated_at);
//                    mapper.map(src -> new AuditorImplementation().getCurrentAuditor(), JpaSale::setCreatedBy);
//                    mapper.map(src -> new AuditorImplementation().getCurrentAuditor(), JpaSale::setModifiedBy);
//                });

//        modelMapper.createTypeMap(JpaSale.class, SaleView.class)
//                .addMappings(mapper -> {
//                    mapper.map(src -> {
//                        double price = src.getPrice();
//                        System.out.println("Price: " + src);
//
//                        double total = price * 2.0; // Ensure multiplication is done using double values
//                        System.out.println("Total: " + total);
//
//                        return total;
//                    },SaleView::setTotal);
//                });

        //(src.getPrice() * (double) src.getQuantity())
        modelMapper.createTypeMap(JpaSale.class, SaleView.class)
                .addMappings(mapper -> {
                    mapper.map(SaleMapper::get,SaleView::setTotal);
                });

//        modelMapper.createTypeMap(JpaSale.class, SaleView.class)
//                .addMappings(mapper -> mapper.map(this::getTotal, SaleView::setTotal));

    }
    private double calculateTotal(JpaSale src) {
        return src.getPrice() * (double) src.getQuantity();
    }
    private static Double get(JpaSale src) {
        return (2.0*src.getQuantity());
    }

    public SaleView convertToView(JpaSale jpaSale)
    {
        SaleView saleView = new SaleView();
        //modelMapper.map(jpaSale,saleView);
        saleView.setId(jpaSale.getId());
        saleView.setClient_id(jpaSale.getClient_id());
        saleView.setSeller_id(jpaSale.getSeller_id());
        saleView.setCreation_date(jpaSale.getCreation_date());
        saleView.setTotal(jpaSale.getQuantity() * jpaSale.getPrice());
        return saleView;
    }

    public JpaSale convertToModel(SaleView saleView)
    {
        JpaSale JpaSale = modelMapper.map(saleView,JpaSale.class);
        return JpaSale;
    }

    private Object getTotal(JpaSale src) {
        return calculateTotal((JpaSale) src);
    }
}
