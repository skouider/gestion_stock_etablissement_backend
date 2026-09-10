package com.esnet.service;

import java.util.List;

import com.esnet.beans.Utilisateur;
import com.esnet.beans.Role;

public interface UtilisateurService {

    Utilisateur save(Utilisateur utilisateur);

    Utilisateur update(Long id, Utilisateur utilisateur);

    Utilisateur findById(Long id);

    Utilisateur findByEmail(String email);

    List<Utilisateur> findAll();

    List<Utilisateur> findByRole(Role role);

    void delete(Long id);
}