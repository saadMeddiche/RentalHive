package com.rentalhive.stockManagement.repositories;

import com.rentalhive.stockManagement.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    boolean existsByDevis(Contract contract);
    @Query("SELECT c FROM Contract c JOIN c.devis d JOIN d.demande de WHERE de.date_expiration > CURRENT_TIMESTAMP")
    List<Contract> findContractsByDateExpiration();
}

