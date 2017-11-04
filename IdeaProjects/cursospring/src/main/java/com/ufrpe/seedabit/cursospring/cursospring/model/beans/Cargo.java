package com.ufrpe.seedabit.cursospring.cursospring.model.beans;



import javax.persistence.*;
import java.util.Collection;

@Entity
public class Cargo {

    @Id
    @GeneratedValue
    private int id;
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cargo_autorizacao",
            joinColumns = @JoinColumn(
                    name = "id_cargo", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_autorizacao", referencedColumnName = "id"))
    private Collection<Autorizacao> autorizacoes;

    public Cargo(){

    }
    public Cargo(String nome){
        this.setNome(nome);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(Collection<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }
}
