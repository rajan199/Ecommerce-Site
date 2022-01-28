package com.application.Infibeam.reposatory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.Infibeam.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
