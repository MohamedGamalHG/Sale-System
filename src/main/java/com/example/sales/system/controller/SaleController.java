package com.example.sales.system.controller;

import com.example.sales.system.domain.dtos.sale.SaleUpdate;
import com.example.sales.system.domain.entities.JpaSale;
import com.example.sales.system.service.SaleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Sale")
@RequestMapping("/sale")
@AllArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping("/allSale")
    public ResponseEntity<?> getAll()
    {
        return ResponseEntity.ok(saleService.getAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(saleService.findById(id));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody JpaSale jpaSale)
    {
        return ResponseEntity.ok(saleService.insert(jpaSale));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SaleUpdate saleUpdate)
    {
        return ResponseEntity.ok(saleService.update(saleUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id)
    {
        saleService.delete(id);
        return true;
    }
}
