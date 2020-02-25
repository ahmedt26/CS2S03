import java.util.ArrayList;
import java.util.HashMap;

class Database {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Company> companies = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();

    // The customer's name and wealth will be stored in a HashMap composed of a a string key and integer value.
     private HashMap<String, Long> customerWealthValues = new HashMap<>();
     public HashMap<String, String> companyRootOwner = new HashMap<>();   // A HashMap of a company and the customer who indirectly owns it.



    // All assets have a new command which add the asset's value to the respective owner's wealth
    void insertHome(Home home) {
        homes.add(home);
        long previousValue = customerWealthValues.get(home.getOwnerName());
        long homeValue = home.getValue();
        customerWealthValues.put(home.getOwnerName(), previousValue + homeValue);   // Add home value to customer's wealth.
        // System.out.println( "Adding the home, " + home.getOwnerName() + " now has a wealth of " + customerWealthValues.get(home.getOwnerName()));
    }

    void insertCar(Car car) {
        cars.add(car);
        long previousValue = customerWealthValues.get(car.getOwnerName());
        long carValue = car.getValue();
        customerWealthValues.put(car.getOwnerName(), previousValue + carValue);   // Add home value to customer's wealth.
        // System.out.println("Adding the car, " + car.getOwnerName() + " now has a wealth of " + customerWealthValues.get(car.getOwnerName()));
    }

    void insertCustomer(Customer customer) {
        customers.add(customer);
        customerWealthValues.put(customer.getName(), (long) 0);   // Initialize the customer's wealth to zero.
    }

    void insertCompany(Company company) {  // Insertion method for the new company object.

        String ownerName = company.getOwnerName();
        String companyName = company.getCompanyName();
        String parentOwner;

        long previousValue = 0;
        long companyValue = company.getValue();

        companies.add(company);

        // Check if company is indirectly owned.
        if (companyRootOwner.get(ownerName) != null) {  // If the owner of this company, is a company that exists (and their owner exists).
            // System.out.println(ownerName + " indirectly owns " + companyName);
            parentOwner = companyRootOwner.get(ownerName);   // Set this company's parent owner to the customer's name.
        } else {   // If the owner of this company, is not a company (a customer, so directly ownership)
            // System.out.println(ownerName + " directly owns " + companyName);
            parentOwner = ownerName;
        }
        companyRootOwner.put(companyName, parentOwner);
        previousValue = customerWealthValues.get(parentOwner);

        customerWealthValues.put(ownerName, previousValue + companyValue);   // Add home value to customer's wealth.
        // System.out.println("Adding the company, " + ownerName + " now has a wealth of " + customerWealthValues.get(ownerName));

    }

    void insertPlan(Plan plan) {
        plans.add(plan);
    }

    void insertClaim(Claim claim) {
        claims.add(claim);
    }

    void insertContract(Contract contract) {
        contracts.add(contract);
    }

    Plan getPlan(String name) {
        for (Plan plan : plans) {
            if (plan.name.equals(name))
                return plan;
        }
        return null;
    }

    Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    Company getCompany(String companyName) {
        for (Company company : companies) {
            if (company.getCompanyName().equals(companyName))
                return company;
        }
        return null;
    }

    // A method to get the wealth of a customer to process wealth-based claims.
    long getCustomerWealth(String name) {
        return customerWealthValues.get(name);
    }

    Contract getContract(String name) {
        for (Contract contract : contracts) {
            if (contract.getContractName().equals(name))
                return contract;
        }
        return null;
    }

    /* This function has been updated to output a list
    of homes rather than a single home. In other words,
    an owner may own multiple homes.
     */
    ArrayList<Home> getHomesByOwnerName(String ownerName) {
        ArrayList<Home> result = new ArrayList<>();
        for (Home home : homes) {
            if (home.getOwnerName().equals(ownerName))
                result.add(home);
        }
        return result;
    }


    /* This function has been updated to output a list
    of homes rather than a single home. In other words,
    an owner may own multiple homes.
     */
    ArrayList<Car> getCarsByOwnerName(String ownerName) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getOwnerName().equals(ownerName))
                result.add(car);
        }
        return result;
    }

    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        for (Claim claim : claims) {
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                totalClaimed += claim.getAmount();
        }
        return totalClaimed;
    }

    long totalReceivedAmountByCustomer(String customerName) {
        long totalReceived = 0;
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            if (contract.getCustomerName().equals(customerName)) {
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalReceived;
    }

    Insurable getCarByPlateNumber(String insurableID) {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(insurableID))
                return car;
        }
        return null;
    }

    Insurable getHomeByPostalCode(String insurableID) {
        for (Home home : homes) {
            if (home.getPostalCode().equals(insurableID))
                return home;
        }
        return null;
    }


    // Finds all customers under the given plan name (pulled from my assignment 4).
    long totalCustomersUnderPlan(String planName) {
        long numCustomers = 0;

        // ArrayList<String> existingCustomers = new ArrayList<>();
        for (Contract contract : contracts) {
            Contract targetContract = getContract(contract.getContractName());

            if (targetContract.getPlanName().equals(planName)) {
                numCustomers += 1;
                /*
                As discovered from Piazza, uniqueness of contracts is not covered. Uncomment this code if you want # of unique customers.
                // Checks if this customer has already been added to the list.
                if ( existingCustomers.contains(targetContract.getCustomerName())) { // If they've been accounted for
                    numCustomers += 0;
                } else {
                    existingCustomers.add(targetContract.getCustomerName()); // If not, increment and add their name to the list
                    numCustomers += 1;
                }
                 */
            }

        }
        return numCustomers;
    }

    // Similar to totalReceivedAmountByCustomer, but just searches all claims made by everyone under the specified plan.
    long totalPayedUnderPlan(String planName) {
        long totalPayment = 0;
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());

            // Adds the values of all successful claims to the sum.
            if (contract.getPlanName().equals(planName)) { // Check if contract has same name for the plan
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    totalPayment += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalPayment;
    }
}
