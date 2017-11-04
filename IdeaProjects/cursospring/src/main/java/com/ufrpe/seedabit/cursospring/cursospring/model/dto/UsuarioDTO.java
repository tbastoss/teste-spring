package com.ufrpe.seedabit.cursospring.cursospring.model.dto;

public class UsuarioDTO {

    private String cpf;
    private String senha;

    public UsuarioDTO(){

    }
    public UsuarioDTO(String cpf,String senha){
        this.setCpf(cpf);
        this.setSenha(senha);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
