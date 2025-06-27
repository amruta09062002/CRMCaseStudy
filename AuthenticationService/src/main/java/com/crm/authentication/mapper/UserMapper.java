package com.crm.authentication.mapper;

import com.crm.authentication.dto.RegisterUser;
import com.crm.authentication.entities.User;

public class UserMapper {
	
	public static RegisterUser toRegisterDTO(User user) {
        return new RegisterUser(
        		user.getUserId(),
        		user.getUsername(),
        		user.getPassword(),
        		user.getRole()
        );
    }
 
    public static User toRegisterEntity(RegisterUser register) {
    	return new User(
    			register.getUserId(),
    			register.getUsername(),
    			register.getPassword(),
    			register.getRole()
        );
    }
    
}
