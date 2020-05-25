package com.example.springbootswagger2.controller;

import com.example.springbootswagger2.model.files;
import com.example.springbootswagger2.model.pets;
import com.example.springbootswagger2.repository.FilesRepository;
import com.example.springbootswagger2.repository.PetsRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value="Pet Shop", tags={"Pet Shop"})
public class PetShopController {

    @Autowired
    private PetsRepository petsRepository;

    @ApiOperation(value = "View a list of pets",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @GetMapping("/petshop")
    public List<pets> getAllpets(){
        return petsRepository.showall();
    }





}
