package br.com.fiap.financewalk.controller;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.financewalk.model.Category;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CategoryController {

    private List<Category> repo = List.of(
            new Category(1L, "Lazer", "Popcorn"),
            new Category(2L, "Transporte", "Bus"),
            new Category(3L, "Educação", "Book")).stream()
            .collect(Collectors.toList());

    @GetMapping("/categories")
    public List<Category> index() {
        return repo;
    }

    @PostMapping("/categories")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Category create(@RequestBody Category category) {
        category.setId(new Random().nextLong());
        log.info("Criando categoria: " + category);
        repo.add(category);
        return category;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> get(@PathVariable Long id) {
        log.info("Buscando categoria pelo id: " + id);

        return ResponseEntity.of(
                repo.stream()
                        .filter(c -> c.getId() == id)
                        .findFirst());
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Apagando categoria por id: {}", id);
        repo.removeIf(c -> c.getId() == id);
        return ResponseEntity.noContent().build();
    }

}
