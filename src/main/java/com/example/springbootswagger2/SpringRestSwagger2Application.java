package com.example.springbootswagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringRestSwagger2Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringRestSwagger2Application.class, args);
		openHomePage();
	}

	private static void openHomePage() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("rundll32 url.dll,FileProtocolHandler "+ "https://localhost:8443/login");
		}
	}
