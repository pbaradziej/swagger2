package com.example.springbootswagger2.controller;

import com.example.springbootswagger2.model.breed;
import com.example.springbootswagger2.model.pets;
import com.example.springbootswagger2.repository.BreedRepository;
import com.example.springbootswagger2.repository.PetsRepository;
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
@Api(value="Pet", tags={"Pet"})
public class PetsController {

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private BreedRepository breedRepository;

    @ApiOperation(value = "View a list of available pets",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @GetMapping("/pets")
    public List<pets> getAllpets(){
        return petsRepository.findAll();
    }

    @ApiOperation(value = "Get a pet by Id")
    @GetMapping("/pets/{id}")
    public ResponseEntity<pets> getPetsById(@ApiParam(value = "Pet id from which pet object will retrieve", required = true)@PathVariable(value = "id") int id)
    throws NoSuchElementException{
        pets pets = petsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet not found for this id :: "+ id));
        return ResponseEntity.ok().body(pets);
    }

    @ApiOperation(value = "Add a pet")
    @PostMapping("/breed/{id}")
    public pets createpet(@ApiParam(value = "Breed Id to add breed of a pet", required = true)
                              @PathVariable(value = "id")int id,
            @ApiParam(value = "Pet object store in database table", required = true)@Valid @RequestBody pets pets){
        breed breed = breedRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Breed not found for this id :: "+ id));
        pets.setBreed_id(breed.getId_breed());
        return petsRepository.save(pets);
    }

    @ApiOperation(value = "Update a pet")
    @PutMapping("/pets/{id}")
    public ResponseEntity<pets> updatepet(
            @ApiParam(value = "Pet Id to update pet object", required = true)@PathVariable(value = "id") int id,
            @ApiParam(value = "Update pet object", required = true)@Valid @RequestBody pets petsdetails)
            throws NoSuchElementException{
        pets pets = petsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet not found for this id :: "+ id));

        pets.setId(id);
        pets.setName(petsdetails.getName());
        pets.setBirth(petsdetails.getBirth());
        pets.setSex(petsdetails.getSex());
        final pets updatepet = petsRepository.save(pets);
        return ResponseEntity.ok(updatepet);
    }

    @ApiOperation(value = "Delete a pet")
    @DeleteMapping("/pets/{id}")
    public Map<String,Boolean> deletepet(@ApiParam(value = "Pet Id from which pet object will delete from database table", required = true)@PathVariable(value = "id") int id)
        throws NoSuchElementException{
        pets pets = petsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet not found for this id :: "+ id));
        petsRepository.delete(pets);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

}
