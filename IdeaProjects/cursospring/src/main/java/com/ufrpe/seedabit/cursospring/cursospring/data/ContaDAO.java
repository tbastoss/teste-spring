package com.ufrpe.seedabit.cursospring.cursospring.data;


import com.ufrpe.seedabit.cursospring.cursospring.model.beans.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaDAO  extends JpaRepository<Conta,Integer>{
}
