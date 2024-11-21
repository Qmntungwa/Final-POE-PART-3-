/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.st10270925_nqobile_vumile_mntungwa_assignment_two;
import java.util.Scanner;

/**
 *
 * @author Nqobi
 */
public class UserAuthentication {

    public static void main(String[] args) {
       
        Login system = new Login();
        Scanner sc = new Scanner(System.in);
//  prompting the user to create an account 
        boolean isAccountCreated = false;

        System.out.println("Hi, please create an account");
// using a while loop to repeat the process 
        while (!isAccountCreated) {
            // prompt for first name 
            System.out.print("Enter first name: ");
            String firstName = sc.nextLine();
            system.setfirstname(firstName);
            // prompt for last name 
            System.out.print("Enter last name: ");
            String lastName = sc.nextLine();
            system.setlastname(lastName);
            //prompt for username
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            system.setusername(username);
            // prompt for password 
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            system.setpassword(password);

            String registrationStatus = system.registerUser();
            System.out.println(registrationStatus);
// if statement 
            if (registrationStatus.contains("successfully")) {
                system.CreatAccount(username, password, firstName, lastName);
                isAccountCreated = true;
            }
        }

        System.out.println("Login:");
// using while loop if it is a false input 
        boolean loginStatus = false;

        while (!loginStatus) {
            //prompt for username 
            System.out.print("Enter username: ");
            String loginUsername = sc.nextLine();
//prompt for password 
            System.out.print("Enter password: ");
            String loginPassword = sc.nextLine();

            loginStatus = system.LoginUser(loginUsername, loginPassword);

            String loginMessage = system.returnLoginStatus(loginStatus);
            System.out.println(loginMessage);
        }
    }
}
