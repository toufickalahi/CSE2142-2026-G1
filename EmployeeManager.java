
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static String readEmployeeData() throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(Constants.EMPLOYEE_FILE)
            )
        );

        try {
            return reader.readLine();
        } finally {
            reader.close();
        }
    }

    private static void writeEmployeeData(String data, boolean append)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter(Constants.EMPLOYEE_FILE, append)
        );

        try {
            writer.write(data);
        } finally {
            writer.close();
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please provide an argument.");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployeeData().split(",");

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                System.out.println(employeeData);

                String[] employees = employeeData.split(",");
                Random randomGenerator = new Random();
                int randomIndex = randomGenerator.nextInt(employees.length);

                System.out.println(employees[randomIndex]);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");

            try {
                String employeeName = args[0].substring(1);
                writeEmployeeData(", " + employeeName, true);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployeeData().split(",");
                String searchName = args[0].substring(1);

                for (int index = 0; index < employees.length; index++) {

                    if (employees[index].equals(searchName)) {
                        System.out.println("Employee found!");
                        break;
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();

                String[] words = employeeData.trim().split("[,\\s]+");
                int wordCount = words.length;

                System.out.println(wordCount + " word(s) found");

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                String[] employees = employeeData.split(",");
                String employeeName = args[0].substring(1);

                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(employeeName)) {
                        employees[index] = "Updated";
                    }
                }

                writeEmployeeData(String.join(",", employees), false);

            } catch (Exception exception) {
            }

            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                String[] employees = employeeData.split(",");
                String employeeName = args[0].substring(1);

                List<String> employeeList =
                    new ArrayList<>(Arrays.asList(employees));

                employeeList.remove(employeeName);

                writeEmployeeData(String.join(",", employeeList), false);

            } catch (Exception exception) {
            }

                        System.out.println("Data Deleted.");

        } else {
            System.out.println("Invalid argument!");
        }
    }
}
        
    

