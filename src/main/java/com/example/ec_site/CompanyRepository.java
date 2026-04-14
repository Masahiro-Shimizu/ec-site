package com.example.ec_site;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	List<Company> findByCompanyNameContaining(String companyName);
}
