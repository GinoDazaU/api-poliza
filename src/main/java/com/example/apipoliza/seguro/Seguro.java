package com.example.apipoliza.seguro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Seguro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSeguro;

    @NotNull
    private String nombreSeguro;

    private String descripcion;

    @NotNull
    private BigDecimal monto;
}
