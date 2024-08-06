

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class SalesRecord {
    private int recordId;
    private String salesPerson;
    private String region;
    private double amount;
    private LocalDate date;

    public SalesRecord(int recordId, String salesPerson, String region, double amount, LocalDate date) {
        this.recordId = recordId;
        this.salesPerson = salesPerson;
        this.region = region;
        this.amount = amount;
        this.date = date;
    }

    // Getters
    public int getRecordId() { return recordId; }
    public String getSalesPerson() { return salesPerson; }
    public String getRegion() { return region; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "recordId=" + recordId +
                ", salesPerson='" + salesPerson + '\'' +
                ", region='" + region + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}

public class SalesAnalysis {
    private List<SalesRecord> salesRecords;

    public SalesAnalysis() {
        salesRecords = new ArrayList<>();
        // Add sample data
        salesRecords.add(new SalesRecord(1, "John", "North", 1000.0, LocalDate.of(2024, 1, 15)));
        salesRecords.add(new SalesRecord(2, "Alice", "South", 1500.0, LocalDate.of(2024, 2, 1)));
        salesRecords.add(new SalesRecord(3, "Bob", "East", 800.0, LocalDate.of(2024, 1, 20)));
        salesRecords.add(new SalesRecord(4, "John", "West", 2000.0, LocalDate.of(2024, 2, 10)));
        salesRecords.add(new SalesRecord(5, "Alice", "North", 1200.0, LocalDate.of(2024, 1, 25)));
    }

    public void filterSalesRecords(String region) {
        System.out.println("Sales records for region: " + region);
        salesRecords.stream()
                .filter(record -> record.getRegion().equals(region))
                .forEach(System.out::println);
    }

    public void extractSalesAmounts(String region) {
        System.out.println("Sales amounts for region: " + region);
        salesRecords.stream()
                .filter(record -> record.getRegion().equals(region))
                .map(SalesRecord::getAmount)
                .forEach(System.out::println);
    }

    public void calculateTotalSales(String region) {
        double totalSales = salesRecords.stream()
                .filter(record -> record.getRegion().equals(region))
                .mapToDouble(SalesRecord::getAmount)
                .sum();
        System.out.println("Total sales for region " + region + ": " + totalSales);
    }

    public void groupSalesBySalesPerson() {
        System.out.println("Grouped sales by salesperson:");
        Map<String, List<SalesRecord>> groupedSales = salesRecords.stream()
                .collect(Collectors.groupingBy(SalesRecord::getSalesPerson));
        groupedSales.forEach((salesPerson, records) -> {
            System.out.println(salesPerson + ":");
            records.forEach(record -> System.out.println("  " + record));
        });
    }

    public void generateSalesReport() {
        System.out.println("Sales report by salesperson:");
        Map<String, Double> salesReport = salesRecords.stream()
                .collect(Collectors.groupingBy(SalesRecord::getSalesPerson,
                        Collectors.summingDouble(SalesRecord::getAmount)));
        salesReport.forEach((salesPerson, totalAmount) ->
                System.out.println(salesPerson + ": " + totalAmount));
    }

    public static void main(String[] args) {
        SalesAnalysis salesAnalysis = new SalesAnalysis();

        salesAnalysis.filterSalesRecords("North");
        System.out.println();

        salesAnalysis.extractSalesAmounts("North");
        System.out.println();

        salesAnalysis.calculateTotalSales("North");
        System.out.println();

        salesAnalysis.groupSalesBySalesPerson();
        System.out.println();

        salesAnalysis.generateSalesReport();
    }
}
