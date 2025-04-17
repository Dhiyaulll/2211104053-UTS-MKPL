package lib;

/**
 * Menangani perhitungan pajak untuk karyawan
 */
public class TaxFunction {

    // Mengekstrak magic numbers menjadi konstanta
    private static final double TAX_RATE = 0.05;
    private static final int BASE_NON_TAXABLE_INCOME = 54_000_000;
    private static final int SPOUSE_NON_TAXABLE_INCOME = 4_500_000;
    private static final int CHILD_NON_TAXABLE_INCOME = 1_500_000;
    private static final int MAX_CHILDREN_FOR_TAX_DEDUCTION = 3;

    /**
     * Menghitung pajak penghasilan tahunan untuk karyawan.
     * 
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji bulanan dan penghasilan lain dikalikan dengan
     * bulan bekerja dikurangi deductible) dikurangi penghasilan tidak kena pajak.
     * 
     * Penghasilan tidak kena pajak adalah:
     * - Dasar: Rp 54.000.000
     * - Tambahan Rp 4.500.000 jika menikah
     * - Tambahan Rp 1.500.000 per anak hingga 3 anak
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, 
                                 int deductible, boolean isMarried, int numberOfChildren) {
        
        validateInputs(numberOfMonthWorking, numberOfChildren);
        
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int taxableIncome = Math.max(0, annualIncome - deductible - nonTaxableIncome);
        
        return (int) Math.round(TAX_RATE * taxableIncome);
    }
    
    private static void validateInputs(int numberOfMonthWorking, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }
    }
    
    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int actualChildCount = Math.min(numberOfChildren, MAX_CHILDREN_FOR_TAX_DEDUCTION);
        
        int nonTaxableIncome = BASE_NON_TAXABLE_INCOME;
        
        if (isMarried) {
            nonTaxableIncome += SPOUSE_NON_TAXABLE_INCOME;
        }
        
        nonTaxableIncome += (actualChildCount * CHILD_NON_TAXABLE_INCOME);
        
        return nonTaxableIncome;
    }
    
    private static int calculateAnnualIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking) {
        return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
    }
}