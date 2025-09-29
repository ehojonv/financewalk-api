package br.com.fiap.financewalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.financewalk.model.Transaction;

import java.time.LocalDate;
import java.util.List;


public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findByDescriptionContainingIgnoreCase(String description);

    List<Transaction> findByDescriptionContainingIgnoreCaseAndDate(String description, LocalDate date);
    
}
