package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.entities.*;
import com.rentalhive.stockManagement.generatePDF.GenerateDevis;
import com.rentalhive.stockManagement.generatePDF.TableRow;
import com.rentalhive.stockManagement.repositories.DevisRepository;
import com.rentalhive.stockManagement.services.DevisService;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.helpers.DevisServiceHelper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DevisServiceImp extends DevisServiceHelper implements DevisService {

    DevisRepository devisRepository;

    EquipmentService equipmentService;

    public DevisServiceImp(DevisRepository devisRepository ,EquipmentService equipmentService) {
        super(equipmentService);
        this.devisRepository = devisRepository;
        this.equipmentService = equipmentService;
    }

    @Override
    public List<Devis> getAllDevises() {
        return null;
    }

    @Override
    public Devis addDevis(Devis devis) {
        List<Stock> stock = devis.getDemande().getStocks();

        // Group stocks by equipment and count occurrences
        Map<Equipment, Long> equipmentCountMap = stock.stream()
                .collect(Collectors.groupingBy(Stock::getEquipment, Collectors.counting()));


        List<TableRow> tableRows = equipmentCountMap.entrySet().stream()
                .map(entry -> {
                    Equipment equipment = entry.getKey();
                    long quantity = entry.getValue();
                    long numberOfDays = getNumberOfDaysBetweenTwoDateTimes(devis.getDemande().getDate_reservation(), devis.getDemande().getDate_expiration());
                    double pricePerDay = equipment.getPrice_per_day(); // Retrieve pricePerDay from Equipment
                    double priceTotal = quantity * numberOfDays * pricePerDay;

                    TableRow tableRow = new TableRow();
                    tableRow.setEquipment(equipment.getName()); // Replace with the actual method to get equipment name
                    tableRow.setQuantity(String.valueOf(quantity));
                    tableRow.setPricePerDay(String.valueOf(pricePerDay));
                    tableRow.setPriceTotal(String.valueOf(priceTotal));

                    return tableRow;
                })
                .collect(Collectors.toList());

        // Print or use the tableRows as needed
        for (TableRow tableRow : tableRows) {
            System.out.println(tableRow);
        }

        GenerateDevis generateDevis = new GenerateDevis();

        try {
            generateDevis.fillDevisTemplate("src/main/resources/DevisTemplateV1.pdf", "src/main/resources/devises/devis.pdf", tableRows ,devis.getDemande().getRenter() , devis.getDemande());
        }catch (Exception e){
            e.printStackTrace();
        }





        return null;
    }



    @Override
    public Devis updateDevis(Devis devis) {
        return null;
    }

    @Override
    public boolean isExist(Devis devis){
        return devisRepository.existsById(devis.getId());
    }

    public Double calculateTotalPrice(List<StockQuantity> listOfEquipmentAndQuantity , Demande demande) {

        List<Double> listPriceOfQuantityOfEachEquipmentInOneDay = getTheListPriceOfQuantityOfEachEquipmentInOneDay(listOfEquipmentAndQuantity);

        Double totalPriceOfEquipmentsInOneDay = getTotalPriceOfEquipmentsInOneDay(listPriceOfQuantityOfEachEquipmentInOneDay);

        Long totalDays = getNumberOfDaysBetweenTwoDateTimes(demande.getDate_reservation() , demande.getDate_expiration());

        Double totalPrice  = totalPriceOfEquipmentsInOneDay * totalDays;

        return totalPrice;

    }



}
