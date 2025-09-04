package br.com.fiap.financewalk.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{transaction.description.notblank}") 
    @Size(min = 10, max = 255, message = "{transaction.description.size}")
    private String description;

    @NotNull @Positive(message = "{transaction.amount.positive}")
    private BigDecimal amount;

    @NotNull @PastOrPresent(message = "{transaction.date.pastorpresent}")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

}
