/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.st10270925_nqobile_vumile_mntungwa_assignment_one;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserAuthenticationTest {

    private Login system;

    @BeforeEach
    public void setup() {
        // Initialize a new instance of Login before each test
        system = new Login(); 
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        system.setusername("kyl_1");
        assertTrue(system.checkUsername(), "Username should be correctly formatted");
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        system.setusername("kyle!!!!!!!");
        assertFalse(system.checkUsername(), "Username should be incorrectly formatted");
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        system.setpassword("Ch&&sec@ke99!");
        assertTrue(system.checkcomplexityofpassword(), "Password should meet complexity requirements");
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        system.setpassword("password");
        assertFalse(system.checkcomplexityofpassword(), "Password should not meet complexity requirements");
    }

    @Test
    public void testRegisterUserValidUsernameAndPassword() {
        system.setusername("kyl_1");
        system.setpassword("Ch&&sec@ke99!");
        String registrationStatus = system.registerUser();
        assertEquals("Username successfully captured\nPassword successfully captured", registrationStatus, 
                     "The registration should be successful with valid username and password.");
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        system.setusername("kyle!!!!!!!");
        system.setpassword("Ch&&sec@ke99!");
        String registrationStatus = system.registerUser();
        assertEquals("Username is not in the correct format.", 
                     registrationStatus, 
                     "The registration should fail with an invalid username.");
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        system.setusername("kyl_1");
        system.setpassword("password");
        String registrationStatus = system.registerUser();
        assertEquals("Password is not in the correct format.", 
                     registrationStatus, 
                     "The registration should fail with an invalid password.");
    }

    @Test
    public void testLoginSuccess() {
        system.CreatAccount("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean loginStatus = system.LoginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(loginStatus, "Login should be successful with correct username and password.");
    }

    @Test
    public void testLoginFailure() {
        system.CreatAccount("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean loginStatus = system.LoginUser("kyl_1", "wrongpassword");
        assertFalse(loginStatus, "Login should fail with incorrect password.");
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        system.CreatAccount("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean loginStatus = system.LoginUser("kyl_1", "Ch&&sec@ke99!");
        String loginMessage = system.returnLoginStatus(loginStatus);
        assertEquals("Welcome John, Doe, it's great to see you again.", 
                     loginMessage, 
                     "The login status message should indicate a successful login.");
    }

    @Test
    public void testReturnLoginStatusFailure() {
        system.CreatAccount("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean loginStatus = system.LoginUser("kyl_1", "wrongpassword");
        String loginMessage = system.returnLoginStatus(loginStatus);
        assertEquals("Username or password is incorrect, please try again", 
                     loginMessage, 
                     "The login status message should indicate a failed login.");
    }
}
