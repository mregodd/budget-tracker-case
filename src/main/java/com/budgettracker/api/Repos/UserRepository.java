package com.budgettracker.api.Repos;

import com.budgettracker.api.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unchecked")

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findById(Long id);

    User save(User user);

    User saveAndFlush(User user);

    void deleteById(Long id);
}
