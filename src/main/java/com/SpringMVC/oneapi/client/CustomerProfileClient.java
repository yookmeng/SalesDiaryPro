package com.SpringMVC.oneapi.client;

import com.SpringMVC.oneapi.model.common.AccountBalance; 
import com.SpringMVC.oneapi.model.common.CustomerProfile; 
import com.SpringMVC.oneapi.model.common.LoginResponse; 
 
public interface CustomerProfileClient {  
 
 /**
  * User Login 
  * @return LoginResponse 
  */ 
 LoginResponse login(); 
 
 /**
  * User Logout 
  */ 
 void logout(); 
 
  
 /**
  * Gets logged user customer profile  
  * @return CustomerProfile 
  */ 
 CustomerProfile getCustomerProfile(); 
 
 /**
  * Gets customer profile for specific userId 
  * @return CustomerProfile 
  */ 
 CustomerProfile getCustomerProfileByUserId(int id); 
 
 /**
  * Gets all users for currently logged user. Currently logged user must be a main user 
  * @return CustomerProfile[] 
  */ 
 CustomerProfile[] getCustomerProfiles();  
 
 /**
  * Get logged user account balance 
  * @return AccountBalance 
  */ 
 AccountBalance getAccountBalance(); 
}