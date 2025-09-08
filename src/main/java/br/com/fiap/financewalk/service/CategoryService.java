package br.com.fiap.financewalk.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.financewalk.model.Category;
import br.com.fiap.financewalk.repository.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo repo;

    private ChatClient chat;

    public CategoryService(ChatClient.Builder chatBuilder) {
        this.chat = chatBuilder.build();
    }

    public Category save(Category category) {
        if (category.getIcon().isBlank()) {
            String icon = chat
                            .prompt("responda apenas diretamente, apenas a palavra sem pontuação. indique um ícone do Lucide.dev para a categoria " + category.getName())
                            .call()
                            .content();
            category.setIcon(icon);
        }

        return repo.save(category);
    }
}
