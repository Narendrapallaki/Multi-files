package com.springdto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springdto.model.Student;
@Repository
public interface StudentRep extends JpaRepository<Student, Integer>{

}
