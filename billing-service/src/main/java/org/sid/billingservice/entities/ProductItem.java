package org.sid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.sid.billingservice.model.Product;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    private double price;
    private long productId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIgnore   also work
    @ManyToOne
    private Bill bill;
    @Transient
    private Product product;
}
