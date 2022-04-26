package br.com.nava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava.entities.VendasEntity;

@Repository
public interface VendasRepository extends JpaRepository<VendasEntity, Integer> {

}
