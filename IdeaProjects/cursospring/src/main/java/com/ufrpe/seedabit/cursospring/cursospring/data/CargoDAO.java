package com.ufrpe.seedabit.cursospring.cursospring.data;

import com.ufrpe.seedabit.cursospring.cursospring.model.beans.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoDAO extends JpaRepository<Cargo,Integer> {
}
