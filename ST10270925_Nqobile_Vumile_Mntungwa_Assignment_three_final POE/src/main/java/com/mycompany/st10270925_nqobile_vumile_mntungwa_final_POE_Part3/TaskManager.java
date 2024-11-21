package com.mycompany.st10270925_nqobile_vumile_mntungwa_final_POE_Part3;

public class TaskManager {
    // Arrays to store task-related data (developer, task name, task description, etc.)
    String[] developers;
    String[] taskNames;
    String[] taskDescriptions;
    String[] taskStatuses;
    String[] taskIDs;
    int[] taskDurations;
    int taskCount = 0; // Tracks the number of tasks added

    // Constructor initializes the arrays to store a given number of tasks (maxTasks)
    public TaskManager(int maxTasks) {
        developers = new String[maxTasks]; // Array to store developer names
        taskNames = new String[maxTasks]; // Array to store task names
        taskDescriptions = new String[maxTasks]; // Array to store task descriptions
        taskStatuses = new String[maxTasks]; // Array to store task statuses (e.g., "To Do", "Doing", "Done")
        taskIDs = new String[maxTasks]; // Array to store task IDs (auto-generated)
        taskDurations = new int[maxTasks]; // Array to store task durations in hours
    }

    // Adds a new task with the given parameters: developer, task name, description, duration, and status
    public void addTask(String developer, String taskName, String description, int duration, String status) {
        // If the description exceeds 50 characters, throw an exception
        if (!checkTaskDescription(description)) {
            throw new IllegalArgumentException("Please enter a task description of less than 50 characters.");
        }

        // Store task details in the respective arrays
        developers[taskCount] = developer;
        taskNames[taskCount] = taskName;
        taskDescriptions[taskCount] = description;
        taskDurations[taskCount] = duration;
        taskStatuses[taskCount] = status;
        taskIDs[taskCount] = createTaskID(taskName, taskCount, developer); // Generate unique task ID
        taskCount++; // Increment the task count after adding a new task
    }

    // Checks if the task description is less than or equal to 50 characters
    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    // Generates a unique task ID based on task name, task number, and developer's last 3 characters
    public String createTaskID(String taskName, int taskNumber, String developer) {
        // Extract the first two letters of the task name and convert them to uppercase
        String taskInitials = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        // Extract the last three characters of the developer's name and convert them to uppercase
        String devSuffix = developer.length() >= 3 ? developer.substring(developer.length() - 3).toUpperCase() : developer.toUpperCase();
        // Return the formatted task ID in the format: "TaskInitials:TaskNumber:DeveloperSuffix"
        return taskInitials + ":" + taskNumber + ":" + devSuffix;
    }

    // Generates a full report of all tasks, showing the task ID, name, developer, description, duration, and status
    public String showFullReport() {
        if (taskCount == 0) return "No tasks to display."; // Return a message if no tasks exist

        StringBuilder report = new StringBuilder("Full Task Report:\n");

        // Iterate through each task and append its details to the report
        for (int i = 0; i < taskCount; i++) {
            report.append("Task ID: ").append(taskIDs[i]).append("\n")
                    .append("Task Name: ").append(taskNames[i]).append("\n")
                    .append("Developer: ").append(developers[i]).append("\n")
                    .append("Description: ").append(taskDescriptions[i]).append("\n")
                    .append("Duration: ").append(taskDurations[i]).append(" hours\n")
                    .append("Status: ").append(taskStatuses[i]).append("\n\n");
        }
        return report.toString(); // Return the full task report
    }

    // Finds and returns the task with the longest duration
    public String getLongestTask() {
        if (taskCount == 0) return "No tasks available."; // If there are no tasks, return a message

        int longestIndex = 0; // Start by assuming the first task has the longest duration
        for (int i = 1; i < taskCount; i++) {
            // Update the index if a task with a longer duration is found
            if (taskDurations[i] > taskDurations[longestIndex]) {
                longestIndex = i;
            }
        }

        // Return the details of the longest task
        return "Developer: " + developers[longestIndex] + "\n" +
                "Task Name: " + taskNames[longestIndex] + "\n" +
                "Duration: " + taskDurations[longestIndex] + " hours";
    }

    // Searches for a task by its name and returns its details if found
    public String searchTaskByName(String name) {
        for (int i = 0; i < taskCount; i++) {
            if (taskNames[i].equalsIgnoreCase(name)) {
                return "Task Name: " + taskNames[i] + "\n" +
                        "Developer: " + developers[i] + "\n" +
                        "Status: " + taskStatuses[i];
            }
        }
        return "Task not found."; // If task not found, return a message
    }

    // Searches for tasks assigned to a specific developer and returns their details
    public String searchTasksByDeveloper(String developerName) {
        StringBuilder tasks = new StringBuilder("Tasks assigned to " + developerName + ":\n");
        for (int i = 0; i < taskCount; i++) {
            if (developers[i].equalsIgnoreCase(developerName)) {
                tasks.append("Task Name: ").append(taskNames[i]).append("\n")
                        .append("Status: ").append(taskStatuses[i]).append("\n\n");
            }
        }
        return tasks.length() > 0 ? tasks.toString() : "No tasks assigned to " + developerName + "."; // If no tasks found, return a message
    }

    // Deletes a task by its name, shifting the remaining tasks in the array
    public void deleteTask(String taskName) {
        for (int i = 0; i < taskCount; i++) {
            if (taskNames[i].equalsIgnoreCase(taskName)) {
                // Shift the tasks in the arrays to remove the task at index 'i'
                for (int j = i; j < taskCount - 1; j++) {
                    developers[j] = developers[j + 1];
                    taskNames[j] = taskNames[j + 1];
                    taskDescriptions[j] = taskDescriptions[j + 1];
                    taskStatuses[j] = taskStatuses[j + 1];
                    taskIDs[j] = taskIDs[j + 1];
                    taskDurations[j] = taskDurations[j + 1];
                }
                taskCount--; // Decrement the task count after deletion
                return;
            }
        }
    }

    // Returns the total duration of all tasks combined
    public int returnTotalHours() {
        int totalHours = 0;
        for (int i = 0; i < taskCount; i++) {
            totalHours += taskDurations[i]; // Add the duration of each task to the total
        }
        return totalHours; // Return the total hours
    }

    // Prints the details of a specific task based on its index
    public String printTaskDetails(int taskIndex) {
        return """
                Task Status: %s
                Developer Details: %s
                Task Number: %d
                Task Name: %s
                Task Description: %s
                Task ID: %s
                Duration: %d hours
                """.formatted(taskStatuses[taskIndex], developers[taskIndex], taskIndex, taskNames[taskIndex], taskDescriptions[taskIndex], taskIDs[taskIndex], taskDurations[taskIndex]);
    }

    // Returns the total number of tasks currently stored in the TaskManager
    public int getTaskCount() {
        return taskCount;
    }

    // Returns all tasks with a status of "Done"
    public String getTasksWithStatusDone() {
        StringBuilder doneTasks = new StringBuilder("Tasks with Status 'Done':\n");
        for (int i = 0; i < taskCount; i++) {
            if ("Done".equalsIgnoreCase(taskStatuses[i])) {
                doneTasks.append("Task Name: ").append(taskNames[i]).append("\n")
                        .append("Developer: ").append(developers[i]).append("\n")
                        .append("Duration: ").append(taskDurations[i]).append(" hours\n\n");
            }
        }
        return doneTasks.length() > 25 ? doneTasks.toString() : "No tasks with status 'Done'.";
    }
}
