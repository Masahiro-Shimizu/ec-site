package com.example.ec_site;

import org.springframework.data.jpa.repository.JpaRepository;

//companiesテーブルへのCRUD操作を担当
public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
