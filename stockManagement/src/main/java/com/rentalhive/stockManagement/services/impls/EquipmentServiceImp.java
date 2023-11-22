package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Equipment;import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;
import com.rentalhive.stockManagement.services.CategoryService;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.UserService;
import com.rentalhive.stockManagement.services.helpers.EquipmentServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImp extends EquipmentServiceHelper implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    public EquipmentServiceImp(EquipmentRepository equipmentRepository, UserService userService, CategoryService categoryService) {
        super(equipmentRepository, userService, categoryService);
    }

    public List<Equipment> getAllEquipments() {

        List<Equipment> equipments = equipmentRepository.findAll();

        validationAfterGettingAllEquipments(equipments);

        return equipments;
    }

    public Optional<Equipment> findById(Equipment equipment) {

        throwExceptionIfIdOfEquipmentIsNull(equipment);

        return equipmentRepository.findById(equipment.getId());
    }

    public Equipment findById(Long id) {

        throwExceptionIfIdOfEquipmentIsNull(id);

        Optional<Equipment> equipment = equipmentRepository.findById(id);

        thowExceptionIfEquipmentIsEmpty(equipment);

        return equipment.get();
    }

    public Equipment addEquipment(Equipment equipment) {

        validateEquipmentOnAdding(equipment);

        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Equipment equipment) {

        validateEquipmentOnUpdating(equipment);

        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Equipment equipment) {

        validateEquipmentOnDeleting(equipment);

        equipmentRepository.delete(equipment);

    }

    public Optional<Equipment> findById(long id){
        return equipmentRepository.findById(id);
    }


    public Integer countAvailableStocksForEquipment(Equipment equipment, Demande demande){
        return equipmentRepository.CountStocksWithSpecificEquipmentAndDemandConditions(equipment,demande.getDate_reservation(),demande.getDate_expiration());
    }


    public boolean isExist(Equipment equipment){

        return equipmentRepository.existsById(equipment.getId());
    }

    public List<Stock> getStocksByEquipemntQuantity(Equipment equipment, Integer quantity, Demande demande){
        Pageable pageable = PageRequest.of(0, quantity);
        return equipmentRepository.findStocksWithSpecificEquipmentAndDemandConditions(equipment,demande.getDate_reservation(),demande.getDate_expiration(), pageable);
    }

}
