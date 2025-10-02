package br.com.fiap.financewalk.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.financewalk.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction>{

    // SELECT * Transaction WHERE UPPER(description) like UPPER(%Java%)
    List<Transaction> findByDescriptionContainingIgnoreCase(String description);

    List<Transaction> findByDescriptionContainingIgnoreCaseAndDate(String description, LocalDate date);

    List<Transaction> findByDate(LocalDate date);
    
    //Java Persistence Query Language
    //@Query("SELECT t FROM Transaction WHERE date < now()")
    //List<Transaction> buscaPersonalizada(String criterio);


    
}
