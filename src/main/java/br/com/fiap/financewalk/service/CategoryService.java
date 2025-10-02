package br.com.fiap.financewalk.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.financewalk.model.Category;
import br.com.fiap.financewalk.repository.CategoryRepository;

@Service
public class CategoryService {
 
    @Autowired
    private CategoryRepository categoryRepository;

    private ChatClient chat;

    public CategoryService(ChatClient.Builder chatBuilder){
        this.chat = chatBuilder.build();
    }

    public Category save(Category category) {
        if(category.getIcon().isEmpty()){
            String icon = chat
                    .prompt("indique um ícone do Lucide.dev para a categoria " + category.getName() + ". Retorne apenas o nome do ícone com a primeira letra maiuscula.")
                    .call()
                    .content();

            category.setIcon(icon);
        }
        return categoryRepository.save(category);
    }
    
}
