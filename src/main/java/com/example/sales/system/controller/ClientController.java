package com.example.sales.system.controller;

import com.example.sales.system.domain.dtos.client.ClientView;
import com.example.sales.system.domain.entities.JpaClient;
import com.example.sales.system.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Client")
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/allClient")
    public ResponseEntity<?> getAll()
    {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody ClientView clientView)
    {
        return ResponseEntity.ok(clientService.insert(clientView));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClientView clientView)
    {
        return ResponseEntity.ok(clientService.update(clientView));
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id)
    {
        clientService.delete(id);
        return true;
    }
}
