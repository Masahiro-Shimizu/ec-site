package com.example.ec_site;

import org.springframework.data.jpa.repository.JpaRepository;

// categoriesテーブルへのCRUD操作を担当
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
