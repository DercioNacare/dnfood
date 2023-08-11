package com.dncode.dnfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dncode.dnfood.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
}
