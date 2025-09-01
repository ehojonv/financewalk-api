package br.com.fiap.financewalk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.financewalk.model.Category;
import br.com.fiap.financewalk.repository.CategoryRepo;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryRepo repo;

    @GetMapping
    public List<Category> index() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Category create(@RequestBody Category category) {
        log.info("Criando categoria: " + category);
        return repo.save(category);
    }

    @GetMapping("{id}")
    public Category get(@PathVariable Long id) {
        log.info("Buscando categoria pelo id: " + id);
        return getCategoryById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Apagando categoria por id: {}", id);
        repo.delete(getCategoryById(id));
    }

    @PutMapping("{id}")
    public Category update(@PathVariable Long id, @RequestBody Category updCategory) {
        log.info("Atualizando categoria: {}, com id: {}", updCategory, id);

        getCategoryById(id);
        updCategory.setId(id);
        return repo.save(updCategory);

    }

    private Category getCategoryById(Long id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Categoria não encontrada com id: " + id));

    }
}
