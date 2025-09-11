package br.com.fiap.financewalk.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.financewalk.model.Category;
import br.com.fiap.financewalk.model.Transaction;
import br.com.fiap.financewalk.model.TransactionType;
import br.com.fiap.financewalk.repository.CategoryRepo;
import br.com.fiap.financewalk.repository.TransactionRepo;
import jakarta.annotation.PostConstruct;

@Component
public class DataBaseSeeder {

    @Autowired
    private CategoryRepo repoCategory;

    @Autowired
    private TransactionRepo repoTransaction;

    private Random random = new Random();

    @PostConstruct
    public void seeder() {
        var categories = List.of(
                Category.builder().name("Educação").icon("Book").build(),
                Category.builder().name("Lazer").icon("Movie").build(),
                Category.builder().name("Transporte").icon("Dices").build());

        repoCategory.saveAll(categories);

        var descriptions = List.of(
                "Livro de java", "Taxa do Uber", "Mensalidade da Faculdade", "Futebol com Amigos", "Bilhete Único",
                "Cinema domingo");

        for (int i = 0; i < 50; i++) {

            repoTransaction.saveAll(List.of(
                    Transaction.builder()
                            .description(descriptions.get(random.nextInt(descriptions.size())))
                            .amount(BigDecimal.valueOf(random.nextDouble() * 500))
                            .date(LocalDate.now().minusDays(random.nextInt(90)))
                            .type(TransactionType.values()[random.nextInt(TransactionType.values().length)])
                            .category(categories.get(random.nextInt(categories.size())))
                            .build()));
        }

    }
}
