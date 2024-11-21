package com.mycompany.st10270925_nqobile_vumile_mntungwa_final_POE_Part3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        // Initialize TaskManager with a maximum of 5 tasks
        taskManager = new TaskManager(5);
    }

    @Test
    public void testAddTask() {
        taskManager.addTask("Mike Smith", "Create Login", "Short description", 5, "To Do");

        // Validate task count
        assertEquals(1, taskManager.getTaskCount(), "Task count should be 1 after adding a task.");

        // Validate task name and developer
        assertEquals("Create Login", taskManager.taskNames[0], "Task name should be 'Create Login'.");
        assertEquals("Mike Smith", taskManager.developers[0], "Developer should be 'Mike Smith'.");
    }

    @Test
    public void testGenerateTaskID() {
        taskManager.addTask("Mike Smith", "Login", "Short description", 5, "To Do");

        // Validate generated Task ID
        String expectedTaskID = "LO:0:ITH";
        assertEquals(expectedTaskID, taskManager.taskIDs[0], "Task ID should match the expected format.");
    }

    @Test
    public void testDisplayTasksWithStatusDone() {
        taskManager.addTask("Alice Johnson", "Create Reports", "Done task", 3, "Done");
        taskManager.addTask("Bob Brown", "Update Features", "Another task", 7, "Doing");

        // Validate tasks with 'Done' status
        String doneTasks = taskManager.getTasksWithStatusDone();
        assertTrue(doneTasks.contains("Create Reports"), "Tasks with status 'Done' should include 'Create Reports'.");
        assertFalse(doneTasks.contains("Update Features"), "'Update Features' should not appear in 'Done' tasks.");
    }

    @Test
    public void testLongestTask() {
        taskManager.addTask("Developer A", "Task A", "Description A", 5, "Doing");
        taskManager.addTask("Developer B", "Task B", "Description B", 10, "Done");

        // Validate longest task
        String longestTask = taskManager.getLongestTask();
        assertTrue(longestTask.contains("Task B"), "The longest task should be 'Task B'.");
        assertTrue(longestTask.contains("Developer B"), "The developer for the longest task should be 'Developer B'.");
    }

    @Test
    public void testSearchTaskByName() {
        taskManager.addTask("Alice Johnson", "Create Reports", "Description", 3, "Done");
        taskManager.addTask("Bob Brown", "Update Features", "Description", 7, "Doing");

        // Validate task search
        String searchResult = taskManager.searchTaskByName("Create Reports");
        assertTrue(searchResult.contains("Alice Johnson"), "Search result should include developer 'Alice Johnson'.");
        assertTrue(searchResult.contains("Create Reports"), "Search result should include the task name 'Create Reports'.");
    }

    @Test
    public void testDeleteTask() {
        taskManager.addTask("Alice Johnson", "Create Reports", "Description", 3, "Done");
        taskManager.addTask("Bob Brown", "Update Features", "Description", 7, "Doing");

        int initialCount = taskManager.getTaskCount();
        taskManager.deleteTask("Create Reports");

        // Validate task deletion
        assertEquals(initialCount - 1, taskManager.getTaskCount(), "Task count should decrease by 1 after deletion.");
        String searchResult = taskManager.searchTaskByName("Create Reports");
        assertTrue(searchResult.contains("Task not found"), "Deleted task should not be found.");
    }

    @Test
    public void testDeveloperArrayPopulation() {
        taskManager.addTask("Mike Smith", "Create Login", "Description", 5, "To Do");
        taskManager.addTask("Edward Harrington", "Update Features", "Description", 7, "Doing");
        taskManager.addTask("Samantha Paulson", "Create Reports", "Description", 3, "Done");

        // Validate developer array contents
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrington", "Samantha Paulson", null, null};
        assertArrayEquals(expectedDevelopers, taskManager.developers, "Developer array should match the expected values.");
    }

    @Test
    public void testDisplayReport() {
        taskManager.addTask("Alice Johnson", "Create Reports", "Description", 3, "Done");
        taskManager.addTask("Bob Brown", "Update Features", "Description", 7, "Doing");

        // Validate full report contents
        String report = taskManager.showFullReport();
        assertTrue(report.contains("Alice Johnson"), "Report should include developer 'Alice Johnson'.");
        assertTrue(report.contains("Create Reports"), "Report should include the task 'Create Reports'.");
        assertTrue(report.contains("Bob Brown"), "Report should include developer 'Bob Brown'.");
    }
}
