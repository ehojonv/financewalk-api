package br.com.fiap.financewalk.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.hibernate.type.descriptor.jdbc.NCharJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.financewalk.model.Category;
import br.com.fiap.financewalk.model.Transaction;
import br.com.fiap.financewalk.model.TransactionType;
import br.com.fiap.financewalk.repository.CategoryRepository;
import br.com.fiap.financewalk.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private Random random = new Random();

    @PostConstruct
    public void seeder(){
        var categories = List.of(
            Category.builder().name("Educação").icon("Book").build(),
            Category.builder().name("Transporte").icon("Bus").build(),
            Category.builder().name("Lazer").icon("Dices").build()
        );

        categoryRepository.saveAll( categories );

        var descriptions = List.of(
            "Livro de Java da Faculdade", "Mensalidade da Faculdade", "Bilhete Único", "Taxa do Uber",
            "Cinema domingo", "Futebol com amigos", "Cerveja gelada", "Entretenimento"
        );

        for(int i = 0; i < 50; i++){
            transactionRepository.saveAll(List.of(
                Transaction.builder()
                            .description(descriptions.get(random.nextInt(descriptions.size())))
                            .amount(BigDecimal.valueOf( random.nextDouble() * 500 ))
                            .date(LocalDate.now().minusDays(random.nextInt(7)))
                            .type(TransactionType.EXPENSE)
                            .category(categories.get(random.nextInt(categories.size())))
                            .build()
            ));            
        }
        

    }
    
}
