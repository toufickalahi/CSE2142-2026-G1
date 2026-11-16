
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Reads employee data from the employee file.
    private static String loadEmployeeData() throws IOException {
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

    // Writes employee data to the employee file.
    private static void saveEmployeeData(String employeeData, boolean shouldAppend)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter(Constants.EMPLOYEE_FILE, shouldAppend)
        );

        try {
            writer.write(employeeData);
        } finally {
            writer.close();
        }
    }

    public static void main(String[] commandLineArguments) {

        // Checks whether the user provided a command.
        if (commandLineArguments.length == 0) {
            System.out.println("Please provide an argument.");
            return;
        }

        // Displays the list of all employees.
        if (commandLineArguments[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                String[] employeeNames = loadEmployeeData().split(",");

                for (String employeeName : employeeNames) {
                    System.out.println(employeeName);
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        // Displays employee data and selects a random employee.
        } else if (commandLineArguments[0].equals("s")) {
            System.out.println("Loading data ...");

            try {
                String employeeDataLine = loadEmployeeData();
                System.out.println(employeeDataLine);

                String[] employeeNames = employeeDataLine.split(",");
                Random randomGenerator = new Random();
                int selectedEmployeeIndex =
                    randomGenerator.nextInt(employeeNames.length);

                System.out.println(employeeNames[selectedEmployeeIndex]);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        // Adds a new employee to the file.
        } else if (commandLineArguments[0].contains("+")) {
            System.out.println("Loading data ...");

            try {
                String newEmployeeName = commandLineArguments[0].substring(1);
                saveEmployeeData(", " + newEmployeeName, true);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        // Searches for an employee by name.
        } else if (commandLineArguments[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                String[] employeeNames = loadEmployeeData().split(",");
                String employeeNameToFind = commandLineArguments[0].substring(1);

                for (int employeeIndex = 0;
                     employeeIndex < employeeNames.length;
                     employeeIndex++) {

                    if (employeeNames[employeeIndex].equals(employeeNameToFind)) {
                        System.out.println("Employee found!");
                        break;
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        // Counts the words in the employee data.
        } else if (commandLineArguments[0].contains("c")) {
            System.out.println("Loading data ...");

            try {
                String employeeDataLine = loadEmployeeData();

                String[] employeeWords = employeeDataLine.trim().split("[,\\s]+");
                int totalWordCount = employeeWords.length;

                System.out.println(totalWordCount + " word(s) found");

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        // Updates an employee's name.
        } else if (commandLineArguments[0].contains("u")) {
            System.out.println("Loading data ...");

            try {
                String employeeDataLine = loadEmployeeData();
                String[] employeeNames = employeeDataLine.split(",");
                String employeeNameToUpdate = commandLineArguments[0].substring(1);

                for (int employeeIndex = 0;
                     employeeIndex < employeeNames.length;
                     employeeIndex++) {

                    if (employeeNames[employeeIndex].equals(employeeNameToUpdate)) {
                        employeeNames[employeeIndex] = "Updated";
                    }
                }

                saveEmployeeData(String.join(",", employeeNames), false);

            } catch (Exception exception) {
            }

            System.out.println("Data Updated.");

        // Deletes an employee from the file.
        } else if (commandLineArguments[0].contains("d")) {
            System.out.println("Loading data ...");

            try {
                String employeeDataLine = loadEmployeeData();
                String[] employeeNames = employeeDataLine.split(",");
                String employeeNameToDelete = commandLineArguments[0].substring(1);

                List<String> employeeList =
                    new ArrayList<>(Arrays.asList(employeeNames));

                employeeList.remove(employeeNameToDelete);

                saveEmployeeData(String.join(",", employeeList), false);

            } catch (Exception exception) {
            }

            System.out.println("Data Deleted.");

        // Handles commands that do not match any supported operation.
        } else {
            System.out.println("Invalid argument!");
        }
    }
}
