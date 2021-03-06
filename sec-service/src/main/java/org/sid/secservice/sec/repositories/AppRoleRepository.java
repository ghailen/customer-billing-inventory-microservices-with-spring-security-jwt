package org.sid.secservice.sec.repositories;

import org.sid.secservice.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository  extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);

}
