package com.alura.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.service.LiteraluraService;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository personRepository;

	private LiteraluraService literaluraService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		literaluraService = new LiteraluraService(bookRepository, personRepository);
		literaluraService.init();
	}

}