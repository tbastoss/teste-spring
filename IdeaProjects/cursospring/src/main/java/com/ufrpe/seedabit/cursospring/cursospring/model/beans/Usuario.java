package com.ufrpe.seedabit.cursospring.cursospring.model.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario {

    @Id
    private String cpf;

    private String nome;

    private String senha;

    @ManyToOne(targetEntity = Cargo.class)
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    public Usuario(){

    }
    public Usuario(String cpf,String senha){
        this.setCpf(cpf);
        this.setSenha(senha);
    }

    public Usuario(String cpf, String nome, String senha, Cargo cargo) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}

