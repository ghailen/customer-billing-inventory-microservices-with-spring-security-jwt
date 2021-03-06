package com.ghailene.mss.controllers;

import com.ghailene.mss.entities.Produit;
import com.ghailene.mss.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// on n a pas besoin de faire tous ça, il suffit d'utiliser  @RepositoryRestResource
/*
@RestController
public class ProduitRestController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping(path="/produits")
    public List<Produit> list(){
        return produitRepository.findAll();
    }

    @GetMapping(path="/produits/{id}")
    public Produit getOne(@PathVariable Long id ){
        return produitRepository.findById(id).get();
    }

    @PostMapping(path="/produits")
    public Produit save(@RequestBody Produit produit){
        return produitRepository.save(produit);
    }

    @PutMapping(path="/produits/{id}")
    public Produit update(@PathVariable Long id,@RequestBody Produit produit){
     produit.setId(id);
        return produitRepository.save(produit);
    }

    @DeleteMapping(path="/produits/{id}")
    public void delete(@PathVariable Long id ){
      produitRepository.deleteById(id);
    }
}*/
