package com.springdto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springdto.model.Teacher;
@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer>{

	 List<Teacher> findByName(String name);
}
