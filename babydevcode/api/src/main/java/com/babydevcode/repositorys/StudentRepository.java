package com.babydevcode.repositorys;

import com.babydevcode.entitys.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findByNameContaining(String name, Pageable pageable);

    @Query(value = "select count(*) > 0 from students where students.dni =: dni", nativeQuery = true)
    Boolean existsByDni(@Param("dni") Long dni);
}
