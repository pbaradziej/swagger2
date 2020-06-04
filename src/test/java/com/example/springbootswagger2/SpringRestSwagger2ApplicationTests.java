package com.example.springbootswagger2;

import com.example.springbootswagger2.controller.FileController;
import com.example.springbootswagger2.controller.PetsController;
import com.example.springbootswagger2.model.files;
import com.example.springbootswagger2.model.pets;
import com.example.springbootswagger2.repository.FilesRepository;
import com.example.springbootswagger2.repository.PetsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringRestSwagger2ApplicationTests {

	@Autowired
	private PetsController petsController;

	@Autowired
	private FileController fileController;

	@Autowired
	private FilesRepository filesRepository;

	@Autowired
	private PetsRepository petsRepository;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EntityManager entityManager;

	@Test
	void contextLoads() {
		assertThat(petsController).isNotNull();
		assertThat(fileController).isNotNull();
	}

	@Test
	void injectedComponentsarenotnull() {
		assertThat(petsRepository).isNotNull();
		assertThat(filesRepository).isNotNull();
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
	}

	@Test
	void whenInitializedByDB() {
		Optional<pets> pets = petsRepository.findById(1);
		assertThat(pets).isNotNull();
		Optional<files> files = filesRepository.findById(1);
		assertThat(files).isNotNull();
	}

}
