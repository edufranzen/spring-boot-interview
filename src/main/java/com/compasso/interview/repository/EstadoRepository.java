package com.compasso.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compasso.interview.entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
