package com.application.Infibeam.reposatory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.Infibeam.model.Role;
import com.application.Infibeam.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String roleName);
    
    @Query("select r from Role r where r.name NOT IN('ROLE_ADMIN')")
    List<Role> getAllRoles();
}