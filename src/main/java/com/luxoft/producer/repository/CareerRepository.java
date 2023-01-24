package com.luxoft.producer.repository;

import com.luxoft.producer.model.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, String> {

}
