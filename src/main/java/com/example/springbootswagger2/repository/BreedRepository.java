package com.example.springbootswagger2.repository;


import com.example.springbootswagger2.model.breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends JpaRepository<breed, Integer> {


}
