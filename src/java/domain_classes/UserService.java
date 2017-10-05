/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain_classes;

/**
 *
 * @author 715060
 */
public class UserService {
    
    public UserService(){
        
    }
    
    public User login(String username, String password){
        User user = null; 
        user = new User(username, password); 
        
        return user; 
    }
    
}
