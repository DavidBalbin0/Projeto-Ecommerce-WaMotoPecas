package me.balbino.wamotopecas.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profile implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProfile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProfile() {
        return nameProfile;
    }

    public void setNameProfile(String nomePerfil) {
        this.nameProfile = nomePerfil;
    }

    @Override
    public String getAuthority() {
        return this.nameProfile;
    }
}
