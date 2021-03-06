package com.ghailene.mss.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor@NoArgsConstructor
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private double quantity;
}


//utiliser pour juste ne pas lire tous les colonnes d'une table

/**
 *
 * Any @Projection interface found in the same package as your entity definitions (or one of it’s sub-packages) is registered
 */
@Projection(name="mobile",types= Produit.class)
interface ProduitProjection {
    // c'est le getter du class produit ,on va juste récuperer le nom
    String getName();
}

@Projection(name="web",types = Produit.class)
interface ProduitProjection2 {
    String getName();
    double getPrice();
}




