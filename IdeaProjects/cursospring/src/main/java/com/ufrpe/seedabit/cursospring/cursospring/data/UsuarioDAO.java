package com.ufrpe.seedabit.cursospring.cursospring.data;

import com.ufrpe.seedabit.cursospring.cursospring.model.beans.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, String> {
}
