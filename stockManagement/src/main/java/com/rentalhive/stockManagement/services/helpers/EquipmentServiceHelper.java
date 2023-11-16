package com.rentalhive.stockManagement.services.helpers;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;

public class EquipmentServiceHelper {

    Predicate<Equipment> isIdOfEquipmentNull = equipment -> equipment.getId() == null;

    /**
     * Checks if the given ID is null.
     *
     * @param id the ID to check
     * @return true if the ID is null, false otherwise
     */
    protected void checkIfIdIsNull(Long id) {

        Equipment equipment = new Equipment();

        equipment.setId(id);

        if (isIdOfEquipmentNull.test(equipment)) {
            throw new ValidationException(List.of("The ID of equipment can not be null"));
        }

    }

   
}
