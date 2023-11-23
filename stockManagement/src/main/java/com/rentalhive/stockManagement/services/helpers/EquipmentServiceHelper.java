package com.rentalhive.stockManagement.services.helpers;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.rentalhive.stockManagement.services.CategoryService;
import com.rentalhive.stockManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.EmptyListException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;
import com.rentalhive.stockManagement.services.impls.CategoryServiceImp;
import com.rentalhive.stockManagement.services.impls.UserServiceImp;
import lombok.RequiredArgsConstructor;

@Component
public class EquipmentServiceHelper extends ServiceHelper {

    private EquipmentRepository equipmentRepository;

    private UserService userService;

    private CategoryService categoryService;
    public EquipmentServiceHelper(EquipmentRepository equipmentRepository, UserService userService, CategoryService categoryService){
        this.equipmentRepository = equipmentRepository;
        this.userService = userService;
        this.categoryService =categoryService;
    }

    // Check If The ID Is Null
    Predicate<Equipment> isIdOfEquipmentNull = equipment -> equipment.getId() == null;

    // Check If The Equipments Is Empty
    Predicate<List<Equipment>> isEquipmentsEmpty = list -> list.isEmpty();

    // Check If there is an equipment with the same name and category
    Predicate<Equipment> isEquipmentAlreadyExistByNameAndCategory = equipment -> {
        return equipmentRepository.existsByNameAndCategory(equipment.getName(), equipment.getCategory());
    };

    // Check If The User Exists
    Predicate<Equipment> isUserDoNotExist = equipment -> {
        return !userService.isExists(equipment.getAdded_by());
    };

    // Check If The Category Exists
    Predicate<Equipment> isCategoryDoNotExist = equipment -> {
        return !categoryService.isExists(equipment.getCategory());
    };

    // Check If The Equipment Exists
    Predicate<Equipment> isEquipmentDoNotExist = equipment -> {
        return !equipmentRepository.existsById(equipment.getId());
    };

    protected void validationAfterGettingAllEquipments(List<Equipment> equipments) {
        // throw Exception If The Equipments Is Empty
        throwExceptionIfEquipmentsIsEmpty(equipments);
    }

    protected void validateEquipmentOnAdding(Equipment equipment) {

        // Inputes Validation
        validateObject(equipment);

        // throwException If there is an equipment with the same name and category
        throwExceptionIfEquipmentAlreadyExistByNameAndCategory(equipment);

        // throwException If The User exist in the database (user table)
        throwExceptionIfUserDoNotExist(equipment);

        // throwException If The Category exist in the database (category table)
        throwExceptionIfTheCategoryDoNotExist(equipment);
    }

    protected void validateEquipmentOnUpdating(Equipment equipment) {

        // Inputes Validation
        validateObject(equipment);

        // throwException If The ID Is Null
        throwExceptionIfIdOfEquipmentIsNull(equipment);

        // throwException If The Equipment do not exist in the database (equipment table)
        throwExceptionIfTheEquipmentDoNotExist(equipment);

        // throwException If The User exist in the database (user table)
        throwExceptionIfUserDoNotExist(equipment);

        // throwException If The Category exist in the database (category table)
        throwExceptionIfTheCategoryDoNotExist(equipment);

    }

    protected void validateEquipmentOnDeleting(Equipment equipment) {

        // throwException If The ID Is Null
        throwExceptionIfIdOfEquipmentIsNull(equipment);

        // throwException If The Equipment do not exist in the database (equipment table)
        throwExceptionIfTheEquipmentDoNotExist(equipment);

    }



    protected void throwExceptionIfTheEquipmentDoNotExist(Equipment equipment) {
        // throwException If The Equipment do not exist in the database (equipment table)
        if (isEquipmentDoNotExist.test(equipment)) {
            throw new DoNotExistsException("The equipment does not exist");
        }
    }

    protected void throwExceptionIfUserDoNotExist(Equipment equipment) {
        // throwException If The User exist in the database (user table)
        if (isUserDoNotExist.test(equipment)) {
            throw new DoNotExistsException("The user does not exist");
        }
    }

    protected void throwExceptionIfTheCategoryDoNotExist(Equipment equipment) {
        // throwException If The Category exist in the database (category table)
        if (isCategoryDoNotExist.test(equipment)) {
            throw new DoNotExistsException("The category does not exist");
        }
    }

    protected void throwExceptionIfEquipmentAlreadyExistByNameAndCategory(Equipment equipment) {
        // throwException If there is an equipment with the same name and category
        if (isEquipmentAlreadyExistByNameAndCategory.test(equipment)) {
            throw new ValidationException(List.of("An equipment with the same name and category already exists"));
        }
    }

    protected void throwExceptionIfIdOfEquipmentIsNull(Equipment equipment) {
        // throwException If The ID of equipment Is Null
        if (isIdOfEquipmentNull.test(equipment)) {
            throw new ValidationException(List.of("The ID of equipment can not be null"));
        }

    }

    protected void throwExceptionIfEquipmentsIsEmpty(List<Equipment> equipments) {
        // throwException If The List Of Equipments Is Empty
        if (isEquipmentsEmpty.test(equipments)) {
            throw new EmptyListException("There are no Equipments");
        }
    }

    public void throwExceptionIfIdOfEquipmentIsNull(Long id) {
        throwExceptionIfObjectIsNull(id, "The ID of Equipment can not be null");
    }

    public void thowExceptionIfEquipmentIsEmpty(Optional<Equipment> equipment) {
        throwExceptionIfOptionalObjectIsEmpty(equipment, "The Equipment do not exist");
    }

}
