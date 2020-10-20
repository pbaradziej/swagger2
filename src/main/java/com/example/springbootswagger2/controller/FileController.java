package com.example.springbootswagger2.controller;


import com.example.springbootswagger2.model.files;
import com.example.springbootswagger2.model.pets;
import com.example.springbootswagger2.repository.FilesRepository;
import com.example.springbootswagger2.repository.PetsRepository;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
@Api(value="Add image", tags = {"Images"})
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private PetsRepository petsRepository;

    @ApiOperation(value = "View a list of available images",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @GetMapping("/files")
    public List<files> getAllFiles(){
        return filesRepository.findAll();
    }

    @ApiOperation(value = "Upload image of a pet", authorizations = {@Authorization(value="JWT")})
    @PostMapping("/pets/{id}")
    public files uploadFile(@ApiParam(value = "Pet Id to update image of a pet", required = true)
                                @PathVariable(value = "id")int id,
                            @ApiParam(value = "Pet object store in database table", required = true)
                                @RequestParam("file") MultipartFile uploadfile)
                            throws IOException {
        logger.debug("Single file upload");
        pets pets = petsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet not found for this id :: "+ id));
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(uploadfile.getOriginalFilename()));
        files files = new files(fileName, uploadfile.getContentType(), uploadfile.getBytes());
        files.setId(pets.getId());

        return filesRepository.save(files);
    }

    @ApiOperation(value = "Download image of a pet", authorizations = {@Authorization(value="JWT")})
    @GetMapping("/files/download/{id}")
    public ResponseEntity<Resource> downloadFile(
            @ApiParam(value = "Pet Id to download image of a pet", required = true)@PathVariable(value = "id") int id) {

        files files = filesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet not found for this id :: "+ id));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFilename() + "\"")
                .body(new ByteArrayResource(files.getImg()));
    }

    @ApiOperation(value = "Delete an image")
    @DeleteMapping("/files/delete/{id}")
    public Map<String,Boolean> deleteImg(@ApiParam(value = "Pet Id to delete image from database table", required = true)@PathVariable(value = "id") int id)
            throws NoSuchElementException{
        files files = filesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet not found for this id :: "+ id));
        filesRepository.delete(files);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }


}
