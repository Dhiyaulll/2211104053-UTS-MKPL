package lib;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    // Mengganti field tanggal individual dengan LocalDate
    private LocalDate joinDate;
    private int monthWorkingInYear;

    private boolean isForeigner;
    private Gender gender;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
            int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.joinDate = LocalDate.of(yearJoined, monthJoined, dayJoined);
        this.isForeigner = isForeigner;
        this.gender = gender;

        childNames = new LinkedList<String>();
        childIdNumbers = new LinkedList<String>();
    }

    // Metode lainnya
    /**
     * Menghitung berapa bulan karyawan telah bekerja dalam tahun berjalan
     */
    private int calculateMonthsWorkedInYear() {
        LocalDate now = LocalDate.now();

        if (now.getYear() == joinDate.getYear()) {
            return now.getMonthValue() - joinDate.getMonthValue();
        } else {
            return 12;
        }
    }

    public int getAnnualIncomeTax() {
        monthWorkingInYear = calculateMonthsWorkedInYear();

        boolean hasSpouse = !spouseIdNumber.equals("");
        int numberOfChildren = childIdNumbers.size();

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, hasSpouse, numberOfChildren);
    }
}
