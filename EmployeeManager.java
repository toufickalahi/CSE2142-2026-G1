
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Read employee data from the file
    private static String readEmployeeData() throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream("employees.txt")
            )
        );

        try {
            return reader.readLine();
        } finally {
            reader.close();
        }
    }

    // Write employee data to the file
    private static void writeEmployeeData(String data, boolean append)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter("employees.txt", append)
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

        // List all employees
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                String[] employees = employeeData.split(",");

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        // Select a random employee
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

        // Add a new employee
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");

            try {
                String employeeName = args[0].substring(1);
                writeEmployeeData(", " + employeeName, true);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        // Search for an employee
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                String[] employees = employeeData.split(",");

                boolean found = false;
                String searchName = args[0].substring(1);

                for (int index = 0;
                     index < employees.length && !found;
                     index++) {

                    if (employees[index].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        // Count words
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");

            try {
                String employeeData = readEmployeeData();
                char[] characters = employeeData.toCharArray();

                boolean inWord = false;
                int count = 0;

                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }

                System.out.println(count + " word(s) found " + characters.length);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        // Update an employee
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

        // Delete an employee
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
        }
    }
}
