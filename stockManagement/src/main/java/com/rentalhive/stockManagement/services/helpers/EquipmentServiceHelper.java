package com.rentalhive.stockManagement.services.helpers;

import java.util.List;
import java.util.function.Predicate;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;
import com.rentalhive.stockManagement.services.impls.CategoryServiceImp;
import com.rentalhive.stockManagement.services.impls.UserServiceImp;

public class EquipmentServiceHelper extends ServiceHelper {

    EquipmentRepository equipmentRepository;

    public EquipmentServiceHelper(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
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
        return !new UserServiceImp().isExists(equipment.getAdded_by());
    };

    // Check If The Category Exists
    Predicate<Equipment> isCategoryDoNotExist = equipment -> {
        return !new CategoryServiceImp().isExists(equipment.getCategory());
    };

    // Check If The Equipment Exists
    Predicate<Equipment> isEquipmentExists = equipment -> {
        return equipmentRepository.existsById(equipment.getId());
    };

    protected void validationAfterGettingAllEquipments(List<Equipment> equipments) {

        throwExceptionIfEquipmentsIsEmpty(equipments);
    }

    protected void validateEquipmentOnAdding(Equipment equipment) {

        // Inputes Validation
        validateObject(equipment);

        // throwException If The ID Is Null
        throwExceptionIfIdOfEquipmentIsNull(equipment);

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

        // throwException If The Equipment exist in the database (equipment table)
        throwExceptionIfTheEquipmentExist(equipment);

        // throwException If The User exist in the database (user table)
        throwExceptionIfUserDoNotExist(equipment);

        // throwException If The Category exist in the database (category table)
        throwExceptionIfTheCategoryDoNotExist(equipment);

    }

    protected void validateEquipmentOnDeleting(Equipment equipment) {

        // throwException If The ID Is Null
        throwExceptionIfIdOfEquipmentIsNull(equipment);

        // throwException If The Equipment exist in the database (equipment table)
        throwExceptionIfTheEquipmentExist(equipment);

    }

    protected void throwExceptionIfTheEquipmentExist(Equipment equipment) {
        // throwException If The Equipment exist in the database (equipment table)
        if (isEquipmentExists.test(equipment)) {
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

}
