/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10270925_nqobile_vumile_mntungwa_assignment_one;

/**
 *
 * @author Nqobi
 */
public class Login {
    private String  password; 
    private String  firstname; 
    private String  lastname;
    private String  username; 
    
    public Login(String password,String firstname,String lastname,String username){
     this.password=password; 
     this.firstname=firstname;
     this.lastname=lastname;
     this.username=username; 
     
    }
    
    
    // constructor for class (Login)
    
    public Login(){
                  }
     //setter method
    public void setpassword(String password){
        this.password=password; 
          
    }
    
    public void setfirstname(String firstname){ 
        this.firstname=firstname; 
        
       }
    
    public void setlastname(String lastname){
        this.lastname= lastname; 
        
    } 
    
    public void setusername(String username){
        this.username=username; 
       
    }
    
    // Getters for the methods 
    
    public String getpassword(){
        return this.password; 
        
    }
    
    public String getfirstname(){
        return this.firstname; 
    }
    
    public String gettlastname(){
        return this.lastname; 
    }
    
    public String getusername(){
        return this.username;
    } 
    // boolean that checks username and required field
    public boolean checkUsername(){
        return username.contains("_")&&username.length()<=5; 
    } 
    // Boolean that checks the password 
    public boolean checkcomplexityofpassword(){
        // check these requirments 
        return password.length()>=8&&
                password.matches(".*[A-Z].*")&&
                password.matches(".*[0-9].*")&&
                password.matches(".*[!:@~+&^%$£()].*");  
                
    }
    
    
      // METHOD TO REGISTER THE USER BY CHECKING VALIDILITY 
    public String registerUser(){
    // check weather the user followed the correct formath 
    if (!checkUsername()) {
    return "username is not in the correct format "; 

}
    if (!checkcomplexityofpassword()) {
     return " password is not in the correct format"; 
 }
    return"Username successfully captured\nPassword sucesssfully captured"; 
} 


//Method to confirm log in details 
public boolean LoginUser(String username,String password){
// varify the provided crdentials with what you have
return this.username.equals(username)&&
this.password.equals(password); 

}

public String returnLoginStatus(boolean LoginStatus){
if (LoginStatus){
return "Kumusta"+firstname+", "+lastname+"goods to be in you precence once again "; 
}
// if it fails then display error message 
return "username or password is not correct, try again"; 
}

//Method to create an account 
public void CreatAccount(String username, String password,String firstname,String lastname){
this.username=username;
this.password=password; 
this.firstname=firstname; 
this.lastname=lastname; 
//print if sucessful 
System.out.println("account created sucessfully"); 
}


}


