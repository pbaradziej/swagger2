package com.example.springbootswagger2.controller;

import com.example.springbootswagger2.model.breed;
import com.example.springbootswagger2.repository.BreedRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
@Api(value="Breed", tags={"Breed"})
public class BreedController {

    @Autowired
    private BreedRepository breedRepository;

    @ApiOperation(value = "View a list of available breeds",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @GetMapping("/breed")
    public List<breed> getAllBreeds(){
        return breedRepository.findAll();
    }

    @ApiOperation(value = "Get a breed by Id")
    @GetMapping("/breed/{id}")
    public ResponseEntity<breed> getBreedById(@ApiParam(value = "Breed id from which breed object will retrieve", required = true)@PathVariable(value = "id") int id)
            throws NoSuchElementException{
        breed breed = breedRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Breed not found for this id :: "+ id));
        return ResponseEntity.ok().body(breed);
    }

    @ApiOperation(value = "Add a Breed")
    @PostMapping("/breed")
    public breed createBreed(@ApiParam(value = "Breed object store in database table", required = true)@Valid @RequestBody breed breed){
        return breedRepository.save(breed);
    }

    @ApiOperation(value = "Update a breed")
    @PutMapping("/breed/{id}")
    public ResponseEntity<breed> updateBreed(
            @ApiParam(value = "Breed Id to update breed object", required = true)@PathVariable(value = "id") int id,
            @ApiParam(value = "Update breed object", required = true)@Valid @RequestBody breed breeddetails)
            throws NoSuchElementException{
        breed breed = breedRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Breed not found for this id :: "+ id));

        breed.setId_breed(id);
        breed.setName(breeddetails.getName());
        breed.setPrice(breeddetails.getPrice());
        final breed updatebreed = breedRepository.save(breed);
        return ResponseEntity.ok(updatebreed);
    }

    @ApiOperation(value = "Delete a breed")
    @DeleteMapping("/breed/{id}")
    public Map<String,Boolean> deleteBreed(@ApiParam(value = "Breed Id from which breed object will be deleted from database table", required = true)@PathVariable(value = "id") int id)
            throws NoSuchElementException{
        breed breed = breedRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Breed not found for this id :: "+ id));
        breedRepository.delete(breed);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

}
