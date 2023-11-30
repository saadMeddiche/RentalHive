package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Contract;
import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.generatePDF.GenerateContract;
import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.processors.DevisProcessor;
import com.rentalhive.stockManagement.repositories.ContractRepository;
import com.rentalhive.stockManagement.services.ContractService;
import com.rentalhive.stockManagement.services.DevisService;
import com.rentalhive.stockManagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ContractServiceImp extends ServiceHelper implements ContractService {

     final ContractRepository contractRepository;
     final UserService userService;
     final DevisService devisService;


    public List<Contract> getAllContracts() {
        return null;
    }

    public Contract findById(Long id) {
        Optional<Contract> contract=contractRepository.findById(id);
        if(contract.isPresent()){
            return contract.get();
        }
        throw new DoNotExistsException("this contract doesn't exist");
    }

    public List<Contract> findActiveContract(){
        return contractRepository.findContractsByDateExpiration();
    }
    public Contract addContract(Contract contract) {

        validateContractOnAdding(contract);
        String destinationPath=createDestinationPathForContract(contract);
        createPdfForContract(contract.getDevis().getPathPDF(),destinationPath);

        return contractRepository.save(contract);
    }

    protected void validateContractOnAdding(Contract contract) {

        // Inputs Validation
        validateObject(contract);

        throwExceptionIfTheContractExist(contract);

        // throwException If The User exist in the database (user table)
        throwExceptionIfUserDoNotExist(contract);

        throwExceptionIfDevisDoNotExist(contract);

        throwExceptionIfDateCreationHigherThanDemandeExperationDate(contract);

    }

    protected void throwExceptionIfTheContractExist(Contract contract) {
        if (!contractRepository.existsByDevis(contract)) {
            throw new DoNotExistsException("The contract does exist");
        }
    }
    protected void throwExceptionIfDevisDoNotExist(Contract contract) {
        if (!devisService.isExist(contract.getDevis())) {
            throw new DoNotExistsException("The devis does not exist");
        }
    }

    protected void throwExceptionIfDateCreationHigherThanDemandeExperationDate(Contract contract) {
        if (contract.getDevis().getDemande().getDate_expiration().isBefore(LocalDateTime.now())) {
            throw new DoNotExistsException("The devis does not exist");
        }
    }

    protected void throwExceptionIfUserDoNotExist(Contract contract) {
        // throwException If The User exist in the database (user table)
        if (!userService.isExists(contract.getCreated_by())) {
            throw new DoNotExistsException("The user does not exist");
        }
    }
    public String createDestinationPathForContract(Contract contract){

        // Get Current Time In Milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        return "src/main/resources/contracts/contract-" +
                contract.getDevis().getDemande().getRenter().getUser_name() +
                "-" +
                currentTimeMillis +
                ".pdf";

    }
    public void createPdfForContract(String devisPath , String destinationPath){
        GenerateContract generateContract = new GenerateContract();
        generateContract.GenerateContractPDF(devisPath , destinationPath);
    }
    public boolean isExists(Contract contract) {
        return contractRepository.existsById(contract.getId());
    }
}
