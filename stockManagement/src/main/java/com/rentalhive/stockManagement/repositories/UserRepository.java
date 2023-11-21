package com.rentalhive.stockManagement.repositories;

import com.rentalhive.stockManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
     boolean find(User user);
}

