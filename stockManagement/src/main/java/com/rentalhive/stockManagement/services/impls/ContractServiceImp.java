package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Contract;
import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.exceptions.costums.AlreadyExistsException;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.boot.context.properties.bind.Bindable.listOf;

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

        Contract Newcontract=validateContractOnAdding(contract);
        String destinationPath=createDestinationPathForContract(Newcontract);
        createPdfForContract(Newcontract.getDevis().getPathPDF(),destinationPath);

        return contractRepository.save(contract);
    }

    protected Contract validateContractOnAdding(Contract contract) {

        // Inputs Validation
        validateObject(contract);

        throwExceptionIfTheContractExist(contract);

        // throwException If The User exist in the database (user table)
        throwExceptionIfUserDoNotExist(contract);

        throwExceptionIfDevisDoNotExist(contract);

        Devis devis=devisService.findById(contract.getDevis().getId());

        contract.setDevis(devis);

        throwExceptionIfDevisNotAccepted(contract);

        throwExceptionIfDateCreationHigherThanDemandeExperationDate(contract);
        return contract;

    }

    protected void throwExceptionIfTheContractExist(Contract contract) {
        if (!contractRepository.existsByDevis(contract)) {
            throw new AlreadyExistsException("The contract does exist");
        }
    }
    protected void throwExceptionIfDevisDoNotExist(Contract contract) {
        if (!devisService.isExist(contract.getDevis())) {
            throw new DoNotExistsException("The devis does not exist");
        }
    }

    protected void throwExceptionIfDateCreationHigherThanDemandeExperationDate(Contract contract) {
        if (contract.getDevis().getDemande().getDate_expiration().isBefore(LocalDateTime.now())) {
            throw new ValidationException(List.of("The devis does not exist"));
        }
    }

    protected void throwExceptionIfUserDoNotExist(Contract contract) {
        // throwException If The User exist in the database (user table)
        if (!userService.isExists(contract.getCreated_by())) {
            throw new DoNotExistsException("The user does not exist");
        }
    }
    protected void throwExceptionIfDevisNotAccepted(Contract contract) {
        if (!devisService.isAccepted(contract.getDevis())) {
            throw new ValidationException(List.of("The devis is not accepted yet"));
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
