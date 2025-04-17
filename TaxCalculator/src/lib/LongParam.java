package lib;

/**
 * Menangani perhitungan pajak untuk karyawan
 */
public class TaxFunction {

    private static final double TAX_RATE = 0.05;
    private static final int BASE_NON_TAXABLE_INCOME = 54_000_000;
    private static final int SPOUSE_NON_TAXABLE_INCOME = 4_500_000;
    private static final int CHILD_NON_TAXABLE_INCOME = 1_500_000;
    private static final int MAX_CHILDREN_FOR_TAX_DEDUCTION = 3;
    private static final int MAX_MONTHS_IN_YEAR = 12;

    /**
     * Menghitung pajak penghasilan tahunan menggunakan objek IncomeData
     */
    public static int calculateTax(IncomeData incomeData) {
        validateInputs(incomeData);
        
        int nonTaxableIncome = calculateNonTaxableIncome(incomeData.isMarried(), incomeData.getNumberOfChildren());
        int annualIncome = calculateAnnualIncome(incomeData);
        int taxableIncome = Math.max(0, annualIncome - incomeData.getAnnualDeductible() - nonTaxableIncome);
        
        return (int) Math.round(TAX_RATE * taxableIncome);
    }
    
    /**
     * Metode lama untuk kompatibilitas mundur
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, 
                                 int deductible, boolean isMarried, int numberOfChildren) {
        IncomeData incomeData = new IncomeData(
            monthlySalary, otherMonthlyIncome, numberOfMonthWorking, 
            deductible, isMarried, numberOfChildren
        );
        return calculateTax(incomeData);
    }
    
    // Metode helper lainnya...
}