package com.rentalhive.stockManagement.repositories;

import com.rentalhive.stockManagement.entities.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {
    boolean existsByStatus(Devis.Status status);
}
