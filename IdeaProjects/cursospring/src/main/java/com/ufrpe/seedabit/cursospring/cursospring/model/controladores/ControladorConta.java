package com.ufrpe.seedabit.cursospring.cursospring.model.controladores;

import com.ufrpe.seedabit.cursospring.cursospring.data.ContaDAO;
import com.ufrpe.seedabit.cursospring.cursospring.model.beans.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ControladorConta {

    @Autowired
    private ContaDAO repositorio;

    @CrossOrigin
    @GetMapping("/listar")
    //Verifica se a Autorização no contexto da Thread de execução tem a autorização VISUALIZAR, caso sim o método é acessado, caso contrário
    //o serviço retorna 403 - Unauthorized
    @PreAuthorize("hasAuthority('VISUALIZAR')")
    public ResponseEntity<List<Conta>> listarTodos(){
        return new ResponseEntity<List<Conta>>(this.repositorio.findAll(), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/remover/{id}")
    @PreAuthorize("hasAuthority('REMOVER')")
    public ResponseEntity<Conta> remover(@PathVariable Integer id){
        this.repositorio.delete(id);
        return new ResponseEntity<Conta>( HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/inserir/{id}")
    //Verifica se a Autorização no contexto da Thread de execução tem a autorização CRIAR, caso sim o método é acessado, caso contrário
    //o serviço retorna 403 - Unauthorized
    @PreAuthorize("hasAuthority('CRIAR')")
    public ResponseEntity<Conta> listarTodos(@RequestBody Conta conta){
        this.repositorio.save(conta);
        return new ResponseEntity<Conta>(conta, HttpStatus.OK);
    }

}
