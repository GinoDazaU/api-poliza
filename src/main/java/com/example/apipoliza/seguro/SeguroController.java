package com.example.apipoliza.seguro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seguros")
public class SeguroController {

    @Autowired
    private SeguroService seguroService;

    // Obtener todos los seguros
    @GetMapping
    public List<Seguro> getAllSeguros() {
        return seguroService.getAllSeguros();
    }

    // Obtener un seguro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Seguro> getSeguroById(@PathVariable Long id) {
        Optional<Seguro> seguro = seguroService.getSeguroById(id);
        if (seguro.isPresent()) {
            return new ResponseEntity<>(seguro.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo seguro
    @PostMapping
    public ResponseEntity<Seguro> createSeguro(@RequestBody Seguro seguro) {
        Seguro newSeguro = seguroService.saveSeguro(seguro);
        return new ResponseEntity<>(newSeguro, HttpStatus.CREATED);
    }

    // Actualizar un seguro existente
    @PutMapping("/{id}")
    public ResponseEntity<Seguro> updateSeguro(@PathVariable Long id, @RequestBody Seguro seguroDetails) {
        Optional<Seguro> seguro = seguroService.getSeguroById(id);
        if (seguro.isPresent()) {
            Seguro existingSeguro = seguro.get();
            existingSeguro.setNombreSeguro(seguroDetails.getNombreSeguro());
            existingSeguro.setDescripcion(seguroDetails.getDescripcion());
            existingSeguro.setMonto(seguroDetails.getMonto());

            Seguro updatedSeguro = seguroService.saveSeguro(existingSeguro);
            return new ResponseEntity<>(updatedSeguro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un seguro
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSeguro(@PathVariable Long id) {
        seguroService.deleteSeguro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
