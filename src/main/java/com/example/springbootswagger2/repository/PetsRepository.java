package com.example.springbootswagger2.repository;


import com.example.springbootswagger2.model.pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetsRepository extends JpaRepository<pets, Integer> {


}
