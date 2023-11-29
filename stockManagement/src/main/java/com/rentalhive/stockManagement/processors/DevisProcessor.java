package com.rentalhive.stockManagement.processors;

import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.generatePDF.GenerateDevis;
import com.rentalhive.stockManagement.generatePDF.TableRow;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DevisProcessor {

    public double processDevis(Devis devis , String outPutPath) {

        List<Stock> stock = devis.getDemande().getStocks();

        Map<Equipment, Long> equipmentCountMap = groupStocksByEquipment(stock);

        List<TableRow> tableRows = createTableRows(devis, equipmentCountMap);

        generateDevisPDF(tableRows, devis ,outPutPath);

        return getTotalPriceOfAllEquipment(tableRows);
    }

    private double getTotalPriceOfAllEquipment(List<TableRow> tableRows) {
        return tableRows.stream()
                .mapToDouble(row -> Double.parseDouble(row.getPriceTotal()))
                .sum();
    }

    private  Map<Equipment, Long> groupStocksByEquipment(List<Stock> stock) {
        return stock.stream()
                .collect(Collectors.groupingBy(Stock::getEquipment, Collectors.counting()));
    }

    private  List<TableRow> createTableRows(Devis devis, Map<Equipment, Long> equipmentCountMap) {
        return equipmentCountMap.entrySet().stream()
                .map(entry -> createTableRow(devis, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private  TableRow createTableRow(Devis devis, Equipment equipment, long quantity) {
        long numberOfDays = getNumberOfDaysBetweenTwoDateTimes(devis.getDemande().getDate_reservation(),
                devis.getDemande().getDate_expiration());
        double pricePerDay = equipment.getPrice_per_day();
        double priceTotal = quantity * numberOfDays * pricePerDay;

        TableRow tableRow = new TableRow();
        tableRow.setEquipment(equipment.getName());
        tableRow.setQuantity(String.valueOf(quantity));
        tableRow.setPricePerDay(String.valueOf(pricePerDay));
        tableRow.setPriceTotal(String.valueOf(priceTotal));

        return tableRow;
    }

    private void generateDevisPDF(List<TableRow> tableRows, Devis devis , String outPutPath) {
        GenerateDevis generateDevis = new GenerateDevis();

        String templatePath = "src/main/resources/DevisTemplateV1.pdf";

        try {
            generateDevis.fillDevisTemplate(templatePath,
                    outPutPath, tableRows, devis.getDemande().getRenter(),
                    devis.getDemande());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Long getNumberOfDaysBetweenTwoDateTimes(LocalDateTime startDate , LocalDateTime endDate){
        return ChronoUnit.DAYS.between(startDate, endDate);
    }


}
