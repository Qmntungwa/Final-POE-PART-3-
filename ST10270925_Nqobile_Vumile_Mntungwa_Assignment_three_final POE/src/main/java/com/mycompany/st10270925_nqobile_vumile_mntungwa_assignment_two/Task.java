package com.mycompany.st10270925_nqobile_vumile_mntungwa_assignment_two;

/**
 * Task class representing a single task with properties like name, description, developer, duration, and status.
 */
public class Task {

    private String taskName;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskStatus;

    /**
     * Constructor to initialize a Task with specified details.
     */
    public Task(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
    }

    Task(String login_Feature, int i, String create_Login_to_autheticate_users, String robyn_Harrison, int i0, String to_Do) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Checks if the task description is within the allowed length.
     * @return true if the description is 50 characters or less, false otherwise.
     */
    public boolean checkTaskDescription() {
        return this.taskDescription.length() <= 50;
    }

    /**
     * Generates a unique task ID based on the task name and developer details.
     * @return A string representing the task ID.
     */
    public String createTaskID() {
        return taskName.substring(0, 2).toUpperCase() + ":" + developerDetails.substring(developerDetails.length() - 3).toUpperCase();
    }

    /**
     * Generates a string containing all task details.
     * @return A formatted string with all task information.
     */
    public String printTaskDetails() {
        return "Task Name: " + taskName + "\nTask Description: " + taskDescription + "\nDeveloper Details: " + developerDetails +
                "\nTask Duration: " + taskDuration + " hours\nTask Status: " + taskStatus + "\nTask ID: " + createTaskID();
    }

    /**
     * Getter for task duration.
     * @return The duration of the task in hours.
     */
    public int getTaskDuration() {
        return this.taskDuration;
    }
}