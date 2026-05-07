package com.teonvioncollins.BankSystem;

import com.teonvioncollins.BankSystem.models.BankModel;
import com.teonvioncollins.BankSystem.repos.BankRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner init(BankRepo repo) {
		return args -> {
			if (repo.findByAccountType("SAVINGS") == null) {
				repo.save(new BankModel(null, "SAVINGS", 0));
			}

			if (repo.findByAccountType("CHECKING") == null) {
				repo.save(new BankModel(null, "CHECKING", 0));
			}
		};
	}

}
