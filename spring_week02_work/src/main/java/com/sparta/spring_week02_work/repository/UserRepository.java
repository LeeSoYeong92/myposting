package com.sparta.spring_week02_work.repository;



import com.sparta.spring_week02_work.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);

}
