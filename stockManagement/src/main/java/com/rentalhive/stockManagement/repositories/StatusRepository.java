package com.rentalhive.stockManagement.repositories;

import com.rentalhive.stockManagement.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

}

