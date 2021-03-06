package org.sid.secservice.sec.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    // pour n'est pas le serialiser et afficher dans le json
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    //lors de chargement de user avec eager on récuere directment la liste des roles
    // avec lazy on le charge que lorsqu'on besoin
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> appRoles = new ArrayList<>();
}
