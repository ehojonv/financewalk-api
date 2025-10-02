package br.com.fiap.financewalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.fiap.financewalk.model.Transaction;
import br.com.fiap.financewalk.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public Page<Transaction> getTransactions(Specification<Transaction> specification, Pageable pageable) {
        
        return repository.findAll(specification, pageable);
        // var probe = Transaction.builder()
        //     .description(filters.description())
        //     .date(filters.date())
        //     .build();

        // var matcher = ExampleMatcher.matchingAll()
        //     .withIgnoreNullValues()
        //     .withIgnoreCase()
        //     .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        // var example = Example.of(probe, matcher);

    }
    
}
