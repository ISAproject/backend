package com.example.ISA2023.back.services;

import com.example.ISA2023.back.models.Company;
import com.example.ISA2023.back.models.irepositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

    public Company getById(Long id){
        return companyRepository.findById(id).orElse(null);
    }

    public List<Company> getSearchedCompanies(String content,double rating){
        return companyRepository.findByAddressNameRating(content,rating);
    }

    public List<Company> getSearchedRatingCompanies(double rating){
        return companyRepository.findByRating(rating);

    }
}
