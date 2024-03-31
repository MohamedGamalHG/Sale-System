package com.example.sales.system.controller;

import com.example.sales.system.domain.dtos.product.ProductView;
import com.example.sales.system.domain.entities.JpaProduct;
import com.example.sales.system.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Product")
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/allProduct")
    public ResponseEntity<?> getAll()
    {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody ProductView productView)
    {
        return ResponseEntity.ok(productService.insert(productView));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProductView productView)
    {
        return ResponseEntity.ok(productService.update(productView));
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id)
    {
        productService.delete(id);
        return true;
    }
}
