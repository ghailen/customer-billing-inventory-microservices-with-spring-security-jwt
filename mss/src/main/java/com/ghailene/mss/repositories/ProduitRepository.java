package com.ghailene.mss.repositories;

import com.ghailene.mss.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface ProduitRepository  extends JpaRepository<Produit,Long> {

    //http://localhost:8082/produits/search/findByNameContains?name=hp
   // Page<Produit> findByNameContains(String name, Pageable pageable);
// or

    //http://localhost:8082/produits/search/byName?kw=hp
    @RestResource(path="/byName")
    Page<Produit> findByNameContains(@Param("kw") String name, Pageable pageable);

}
