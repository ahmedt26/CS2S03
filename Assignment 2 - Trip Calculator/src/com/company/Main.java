package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



class Main {
    public static void main(String[] args) {



        // Create new arrays for models and cars. These can be dynamically added to.
        List<String> modelNameList = new ArrayList<String>(); // Will be used to easily search to find car models.
        List<Integer> carPlateList = new ArrayList<Integer>(); // Will be used to easily search to find car models
        List<CarModel> modelList = new ArrayList<CarModel>();
        List<Car> carList = new ArrayList<Car>();

        {
            try (InputStreamReader in = new InputStreamReader(System.in); // Initializing lists to be used for the program. Try-catch just in case anything odd is input.
                 BufferedReader buffer = new BufferedReader(in)) {
                String line;

                while ((line = buffer.readLine()) != null) { // The reader which parses the given inputs. It's able to handle null inputs invalid ones.
                    String[] tokens = line.split("\\s");
                    // System.out.println(Arrays.toString(tokens));


                    switch (tokens[0]) {
                        case "MODEL":  // For some reason this statement isn't catching properly...
                            // System.out.println("MODEL CHECK");
                            modelList.add(new CarModel(tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])));
                            modelNameList.add(tokens[1]);

                            break;
                        case "CAR":
                            // System.out.println("CAR CHECK");
                            carList.add(new Car(modelList.get(modelNameList.indexOf(tokens[1])), Integer.parseInt(tokens[2])));
                            carPlateList.add(Integer.parseInt(tokens[2]));

                            break;
                        case "TRIP":  // Trip test
                            // System.out.println("TRIP CHECK");
                            if (!carList.get(carPlateList.indexOf(Integer.parseInt(tokens[1]))).trip(Double.parseDouble(tokens[2]))) { // Trip failure
                                System.out.println("Not enough fuel for #" + Integer.parseInt(tokens[1]));

                            } else { // If the trip is a success
                                System.out.println("Trip completed successfully for #" + Integer.parseInt(tokens[1]));
                                carList.get(carPlateList.indexOf(Integer.parseInt(tokens[1]))).addTrip(Double.parseDouble(tokens[2]));


                            }

                            break;
                        case "REFILL":  // Refills given car
                            System.out.println("REFILL CHECK");
                            carList.get(carPlateList.indexOf(Integer.parseInt(tokens[1]))).refill();


                            break;

                        case "LONGTRIPS": // Longtrips computation
                            System.out.println("#" + tokens[1] + " made " + carList.get(carPlateList.indexOf(Integer.parseInt(tokens[1]))).longTripsCompute(Double.parseDouble(tokens[2])) + " trips longer than " + tokens[2]);

                            break;

                        case "FINISH":
                            // System.out.println("FINISH");
                            System.exit(0);

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }
    // System.out.println("THIS DIDN'T WORK :(");
    // Arrays.toString(tokens)

}

/*
Test Cases:

MODEL Camry 6.5 58
MODEL Civic 7.5 52
CAR Camry 1111
CAR Camry 2222
CAR Civic 3333
CAR Civic 4444
TRIP 1111 350
TRIP 2222 350
TRIP 3333 350
TRIP 4444 350
TRIP 1111 350
TRIP 2222 350
TRIP 3333 350
TRIP 4444 350
FINISH

MODEL X5 10 68
CAR X5 787878
TRIP 787878 500
TRIP 787878 500
TRIP 787878 10
REFILL 787878
TRIP 787878 500
FINISH

MODEL Camry 6.5 58
MODEL Civic 7.5 52
CAR Camry 1111
CAR Civic 4444
TRIP 1111 50
TRIP 4444 50
TRIP 1111 350
TRIP 4444 350
TRIP 1111 350
TRIP 4444 350
LONGTRIPS 1111 300
LONGTRIPS 4444 300
FINISH
 */