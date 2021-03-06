package com.ghailene.mss;

import com.ghailene.mss.entities.Produit;
import com.ghailene.mss.repositories.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class MssApplication {

	public static void main(String[] args) {
		SpringApplication.run(MssApplication.class, args);
	}
// les beans vont etre lancer lors de l'execution de l'application
	@Bean
	CommandLineRunner start(ProduitRepository produitRepository, RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
//permet d'exposer  les id dans le json
			repositoryRestConfiguration.exposeIdsFor(Produit.class);
			produitRepository.save(new Produit(null,"ord hp 54",6000,3));
			produitRepository.save(new Produit(null,"imprimante epson",2000,13));
			produitRepository.save(new Produit(null,"smart phone",1000,25));
			produitRepository.findAll().forEach(s-> System.out.println(s.getName()));
		};
	}
}
