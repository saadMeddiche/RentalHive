package com.rentalhive.stockManagement.controllers;

import com.rentalhive.stockManagement.dto.ContractDto;
import com.rentalhive.stockManagement.entities.Contract;
import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.helpers.ControllerHelper;
import com.rentalhive.stockManagement.services.ContractService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ContractController extends ControllerHelper {

    final ContractService contractService;
    final ModelMapper modelMapper;

    @GetMapping("/contracts")
    public ResponseEntity<?> getAllContracts() {
        try {

            List<Contract> contracts = contractService.findActiveContract();

            return new ResponseEntity<>(contracts, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @PostMapping("/contracts")
    public ResponseEntity<?> addContract(@RequestBody() ContractDto contractDto) {

        try {
            Contract contract = modelMapper.map(contractDto, Contract.class);

            User user=new User();
            user.setId(1L);
            contract.setCreated_by(user);
            contract.setCreation_Date(LocalDateTime.now());
            Contract addedContract = contractService.addContract(contract);
            ContractDto addContractDto= modelMapper.map(addedContract, ContractDto.class);
            return new ResponseEntity<>(addContractDto, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }
}