package com.esnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esnet.beans.Role;
import com.esnet.beans.Utilisateur;

@Repository
public interface UtilisateurRepository
        extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Utilisateur> findByNomContainingIgnoreCase(String nom);

    List<Utilisateur> findByRole(Role role);
}