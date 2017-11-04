package com.ufrpe.seedabit.cursospring.cursospring.model.beans;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Autorizacao implements GrantedAuthority {

    @Id
    @GeneratedValue
    private int id;

    private String nome;

    public Autorizacao(String nome){
        this.setNome(nome);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }

    public Autorizacao(){

    }
}
