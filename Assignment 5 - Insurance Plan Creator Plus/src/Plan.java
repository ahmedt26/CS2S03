import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

abstract class Plan {
    String name;
    long premium;
    long maxCoveragePerClaim;
    long deductible;
    RangeCriterion customerAgeCriterion = new RangeCriterion();
    RangeCriterion customerIncomeCriterion = new RangeCriterion();
    RangeCriterion customerWealthCriterion = new RangeCriterion();


    Plan(HashMap<String, Tag> tags) {
        name = tags.get("NAME").getValue();
        premium = Integer.parseInt(tags.get("PREMIUM").getValue());
        maxCoveragePerClaim = Integer.parseInt(tags.get("MAX_COVERAGE_PER_CLAIM").getValue());
        deductible = Integer.parseInt(tags.get("DEDUCTIBLE").getValue());

        // Set the range for the age criterion.
        if (tags.get("CUSTOMER.AGELOW") != null) {
            // System.out.println("AGE LOW ADDED");
            customerAgeCriterion.addCriterion(tags.get("CUSTOMER.AGELOW"));
        }
        if (tags.get("CUSTOMER.AGEHIGH") != null) {
            // System.out.println("AGE HIGH ADDED");
            customerAgeCriterion.addCriterion(tags.get("CUSTOMER.AGEHIGH"));
        }
        if (tags.get("CUSTOMER.INCOME") != null) {
            customerIncomeCriterion.addCriterion(tags.get("CUSTOMER.INCOME"));
        }

        // Same setup as the age range bug-fix to check if wealth is within certain range.
        if (tags.get("CUSTOMER.WEALTHLOW") != null) {
            // System.out.println("WEALTH LOW ADDED");
            customerWealthCriterion.addCriterion(tags.get("CUSTOMER.WEALTHLOW"));
        }
        if (tags.get("CUSTOMER.WEALTHHIGH") != null) {
            // System.out.println("WEALTH HIGH ADDED");
            customerWealthCriterion.addCriterion(tags.get("CUSTOMER.WEALTHHIGH"));
        }
    }

    abstract boolean isEligible(Insurable insurable, Date date, long customerWealth);

    abstract ArrayList<? extends Insurable> getInsuredItems(Customer customer, Database database);

    abstract Insurable getInsuredItem(String insurableID, Database database);


    // isEligible now takes the customer's wealth as a parameter to accommodate. for the new wealth-based features.
    boolean isEligible(Customer customer, Date currentDate, long customerWealth) {

        // Extracting the age of the customer
        LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBirthDate = customer.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Takes the period between the two dates.
        long age = Period.between(localBirthDate,localCurrentDate).getYears();

        // Checking if the the age criteria matches.
        if (!customerAgeCriterion.isInRange(age)) {
            // System.out.println("AGE RANGE FAILED");
            return false;
        }
        // System.out.println(customer.getName() + "'s AGE PASSED");

        // System.out.println(customer.getName() + "'s wealth is : " + customerWealth);
        if (!customerWealthCriterion.isInRange(customerWealth)) {
            // System.out.println(customer.getName() + "'s WEALTH FAILED");
            return false;
        }


        // Checking if income is in range
        // System.out.println(customer.getName() + "'s AGE/WEALTH PASSED");
        return customerIncomeCriterion.isInRange(customer.getIncome());
    }


    String getName() {
        return name;
    }

    long getPremium() {
        return premium;
    }

    long getMaxCoveragePerClaim() {
        return maxCoveragePerClaim;
    }

    long getDeductible() {
        return deductible;
    }

}
