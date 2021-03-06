package org.sid.secservice.sec.repositories;

import org.sid.secservice.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByUserName(String username);
}
