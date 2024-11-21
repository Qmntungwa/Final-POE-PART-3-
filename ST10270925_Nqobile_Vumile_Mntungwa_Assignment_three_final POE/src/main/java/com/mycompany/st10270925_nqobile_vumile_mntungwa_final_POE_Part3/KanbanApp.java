package com.mycompany.st10270925_nqobile_vumile_mntungwa_final_POE_Part3;

import javax.swing.*;

public class KanbanApp {
    private static TaskManager taskManager; // TaskManager instance to handle task operations
    private static String registeredUsername; // Registered username for authentication
    private static String registeredPassword; // Registered password for authentication

    public static void main(String[] args) {
        taskManager = new TaskManager(10); // Initialize TaskManager with a capacity of 10 tasks
        displayRegistrationMenu(); // Display registration menu for new users
        loginUser(); // Prompt user to log in with registered credentials
        displayFirstMenu(); // Display the main menu
    }

    // Displays registration menu for new users
    private static void displayRegistrationMenu() {
        boolean registered = false;
        while (!registered) {
            // Get user input for registration details
            String firstname = JOptionPane.showInputDialog("Enter First Name:");
            if (firstname == null) System.exit(0);

            String lastname = JOptionPane.showInputDialog("Enter Last Name:");
            if (lastname == null) System.exit(0);

            String username = JOptionPane.showInputDialog("Create Username (max 5 characters with '_'): ");
            if (username == null) System.exit(0);

            String password = JOptionPane.showInputDialog("Create Password (min 8 characters, 1 uppercase, 1 number):");
            if (password == null) System.exit(0);

            // Validate username and password length
            if (username.length() <= 5 && password.length() >= 8) {
                registeredUsername = username;
                registeredPassword = password;
                registered = true;
                JOptionPane.showMessageDialog(null, "User registered successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed. Please try again.");
            }
        }
    }

    // Prompts user to log in with registered credentials
    private static void loginUser() {
        boolean loggedIn = false;
        while (!loggedIn) {
            // Get user input for login credentials
            String username = JOptionPane.showInputDialog("Enter Username:");
            if (username == null) System.exit(0);

            String password = JOptionPane.showInputDialog("Enter Password:");
            if (password == null) System.exit(0);

            // Validate login credentials
            if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
                JOptionPane.showMessageDialog(null, "Login successful. Welcome, " + username + "!");
                loggedIn = true;
            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Try again.");
            }
        }
    }

    // Displays the main menu
    private static void displayFirstMenu() {
        boolean running = true;
        while (running) {
            // Display menu options and get user choice
            String choice = JOptionPane.showInputDialog(
                    """
                            Main Menu
                            1) Input Tasks
                            2) Show All Tasks
                            3) Manage Tasks
                            4) Quit
                            """);

            if (choice == null) System.exit(0);

            try {
                int option = Integer.parseInt(choice);
                switch (option) {
                    case 1 -> addTasks(); // Add new tasks to the TaskManager
                    case 2 -> JOptionPane.showMessageDialog(null, taskManager.showFullReport(), "All Tasks", JOptionPane.INFORMATION_MESSAGE); // Display all tasks
                    case 3 -> displaySecondMenu(); // Display the task management menu
                    case 4 -> {
                        JOptionPane.showMessageDialog(null, "Goodbye!");
                        running = false;
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }
    }

    // Adds new tasks to the TaskManager
    private static void addTasks() {
        // Get the number of tasks to add
        String numOfTasksInput = JOptionPane.showInputDialog("How many tasks do you want to enter?");
        int numOfTasks;
        try {
            numOfTasks = Integer.parseInt(numOfTasksInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Returning to menu.");
            return;
        }

        int totalHours = 0;

        // Loop to add each task
        for (int i = 0; i < numOfTasks; i++) {
            // Get task details from user input
            String developer = JOptionPane.showInputDialog("Enter Developer Name:");
            if (developer == null) return;

            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            if (taskName == null) return;

            String taskDescription = JOptionPane.showInputDialog("Enter Task Description (max 50 characters):");
            if (taskDescription == null || !taskManager.checkTaskDescription(taskDescription)) {
                JOptionPane.showMessageDialog(null, "Description exceeds 50 characters.");
                return;
            }

            String durationInput = JOptionPane.showInputDialog("Enter Task Duration (in hours):");
            int duration;
            try {
                duration = Integer.parseInt(durationInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid duration. Try again.");
                return;
            }

            // Get task status from user selection
            String[] statusOptions = {"To Do", "Doing", "Done"};
            String status = (String) JOptionPane.showInputDialog(
                    null, "Select Task Status:", "Task Status",
                    JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);
            if (status == null) return;

            // Add the task to the TaskManager and display details
            taskManager.addTask(developer, taskName, taskDescription, duration, status);
            JOptionPane.showMessageDialog(null, taskManager.printTaskDetails(taskManager.getTaskCount() - 1), "Task Details", JOptionPane.INFORMATION_MESSAGE);
            totalHours += duration;
        }

        // Display the total hours across all tasks
        JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + totalHours, "Total Hours", JOptionPane.INFORMATION_MESSAGE);
    }

    // Displays the task management menu
    private static void displaySecondMenu() {
        boolean running = true;
        while (running) {
            // Display menu options and get user choice
            String choice = JOptionPane.showInputDialog(
                    """
                            Manage Tasks
                            1) Search Task by Name
                            2) Search Tasks by Developer
                            3) Display Longest Task
                            4) Display Tasks with Status 'Done'
                            5) Delete Task by Name
                            6) Return to Main Menu
                            """);
            if (choice == null) System.exit(0);

            try {
                int option = Integer.parseInt(choice);
                switch (option) {
                    case 1 -> {
                        String name = JOptionPane.showInputDialog("Enter Task Name to Search:");
                        JOptionPane.showMessageDialog(null, taskManager.searchTaskByName(name), "Search Results", JOptionPane.INFORMATION_MESSAGE); // Search tasks by name
                    }
                    case 2 -> {
                        String developer = JOptionPane.showInputDialog("Enter Developer Name to Search:");
                        JOptionPane.showMessageDialog(null, taskManager.searchTasksByDeveloper(developer), "Developer Tasks", JOptionPane.INFORMATION_MESSAGE); // Search tasks by developer
                    }
                    case 3 -> JOptionPane.showMessageDialog(null, taskManager.getLongestTask(), "Longest Task", JOptionPane.INFORMATION_MESSAGE); // Display the longest task
                    case 4 -> JOptionPane.showMessageDialog(null, taskManager.getTasksWithStatusDone(), "Tasks with Status 'Done'", JOptionPane.INFORMATION_MESSAGE); // Display tasks with status 'Done'
                    case 5 -> {
                        String taskName = JOptionPane.showInputDialog("Enter Task Name to Delete:");
                        taskManager.deleteTask(taskName);
                        JOptionPane.showMessageDialog(null, "Task '" + taskName + "' deleted successfully."); // Delete a task by name
                    }
                    case 6 -> running = false;
                    default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }
    }
}