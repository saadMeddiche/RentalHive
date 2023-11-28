package com.rentalhive.stockManagement.services;

import com.rentalhive.stockManagement.entities.Contract;

import java.util.List;

public interface ContractService {

    public List<Contract> getAllContracts();

    public Contract addContract(Contract contract);

    public Contract findById(Long id);

    public boolean isExists(Contract contract);
    public List<Contract> findActiveContract();
}
