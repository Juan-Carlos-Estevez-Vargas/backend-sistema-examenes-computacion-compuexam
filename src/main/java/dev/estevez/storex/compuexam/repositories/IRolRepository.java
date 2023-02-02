package dev.estevez.storex.compuexam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.estevez.storex.compuexam.entities.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {

}
