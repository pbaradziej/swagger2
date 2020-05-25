package com.example.springbootswagger2.repository;


import com.example.springbootswagger2.model.pets;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetsRepository extends JpaRepository<pets, Integer> {
    @Query("SELECT p.id,p.birth,p.name,p.sex,f.Filename,f.Filename,f.Filetype,f.img FROM pets p JOIN files f on p.id=f.id ORDER BY p.id")
    List<pets> showall();

}
