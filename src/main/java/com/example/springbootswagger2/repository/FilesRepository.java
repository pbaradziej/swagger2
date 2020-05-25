package com.example.springbootswagger2.repository;


import com.example.springbootswagger2.model.files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<files, Integer> {


}
