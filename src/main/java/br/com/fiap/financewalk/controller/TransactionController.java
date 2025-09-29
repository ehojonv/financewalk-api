package br.com.fiap.financewalk.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.financewalk.model.Transaction;
import br.com.fiap.financewalk.repository.TransactionRepo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {

    @Autowired
    TransactionRepo repo;

    @GetMapping
    public List<Transaction> index(@RequestParam(required = false) String description,
            @RequestParam(required = false) LocalDate date) {

        var matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(Transaction.builder()
                .description(description)
                .date(date)
                .build(), matcher);

        return repo.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@RequestBody @Valid Transaction transaction) {
        log.info("criando transação: {}", transaction);
        return repo.save(transaction);
    }

}
