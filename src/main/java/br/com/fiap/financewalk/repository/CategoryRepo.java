package br.com.fiap.financewalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.financewalk.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    
}