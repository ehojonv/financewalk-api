package br.com.fiap.financewalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.financewalk.filter.TransactionSpecification;
import br.com.fiap.financewalk.model.Transaction;
import br.com.fiap.financewalk.model.TransactionFilters;
import br.com.fiap.financewalk.repository.TransactionRepository;
import br.com.fiap.financewalk.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public Page<Transaction> index(TransactionFilters filters, @PageableDefault(size = 10) Pageable pageable) {
        var specification = TransactionSpecification.build(filters);
        return transactionService.getTransactions(specification, pageable);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@RequestBody @Valid Transaction transaction) {
        log.info("cadastrando transação" + transaction);
        return repository.save(transaction);
    }

}
