package com.example.courseapp.repository;

import com.example.courseapp.model.Template;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TemplateRepository extends MongoRepository<Template, String> {
    List<Template> findByCategory(String category);
}