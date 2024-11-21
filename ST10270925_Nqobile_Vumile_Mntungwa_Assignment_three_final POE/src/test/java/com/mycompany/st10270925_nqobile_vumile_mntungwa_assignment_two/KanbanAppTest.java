package com.mycompany.st10270925_nqobile_vumile_mntungwa_assignment_two;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the Task and KanbanApp classes.
 */
public class KanbanAppTest {

    private Task task;
    private List<Task> taskList;

    @BeforeEach
    public void setUp() {
        // Initialize the task and task list before each test
        taskList = new ArrayList<>();
        task = new Task("Login Feature", "Short Description", "Robyn Harrison", 8, "To Do");
    }

    @Test
    public void testTaskDescriptionValidation() {
        // Check a valid task description
        Task validTask = new Task("Valid Task", "Short description", "John Doe", 5, "To Do");
        assertTrue(validTask.checkTaskDescription(), "Task description should be valid");

        // Check an invalid task description (longer than 50 characters)
        Task invalidTask = new Task("Long Task", "This description exceeds fifty characters, so it should be invalid", "Jane Doe", 5, "To Do");
        assertFalse(invalidTask.checkTaskDescription(), "Task description should be invalid due to length");
    }

    @Test
    public void testCreateTaskID() {
        // Test ID generation based on task name and developer details
        Task task = new Task("Login Feature", "Short description", "Robyn Harrison", 8, "To Do");
        String taskID = task.createTaskID();

        // Task ID should be in format <first 2 letters of task name>:<last 3 letters of developer name>
        assertEquals("LO:SON", taskID, "Task ID should match the expected format");
    }

    @Test
    public void testAddTask() {
        // Initialize task list and add a task
        taskList.add(task);
        int initialSize = taskList.size();

        // Add a new task
        Task newTask = new Task("Test Task", "Testing add method", "Alice Smith", 4, "Doing");
        taskList.add(newTask);

        // Validate the list size increase and that the added task matches
        assertEquals(initialSize + 1, taskList.size(), "Task list size should increase after adding a task");
        assertEquals(newTask, taskList.get(initialSize), "The last task in the list should be the newly added task");
    }

    @Test
    public void testShowAllTasks() {
        // Add multiple tasks to task list
        Task task1 = new Task("Task 1", "Description 1", "Dev One", 2, "To Do");
        Task task2 = new Task("Task 2", "Description 2", "Dev Two", 3, "Done");

        taskList.add(task1);
        taskList.add(task2);

        // Generate task list details
        StringBuilder allTasks = new StringBuilder();
        for (Task t : taskList) {
            allTasks.append(t.printTaskDetails()).append("\n\n");
        }

        // Validate that all task details are correctly displayed
        assertTrue(allTasks.toString().contains("Task 1"), "Task details should contain Task 1");
        assertTrue(allTasks.toString().contains("Dev One"), "Task details should contain Developer One's details");
        assertTrue(allTasks.toString().contains("Task 2"), "Task details should contain Task 2");
        assertTrue(allTasks.toString().contains("Dev Two"), "Task details should contain Developer Two's details");
    }
}