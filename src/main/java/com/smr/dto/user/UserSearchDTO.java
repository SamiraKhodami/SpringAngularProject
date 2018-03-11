package com.smr.dto.user;

import com.smr.dto.BaseDTO;
import java.util.Optional;

public class UserSearchDTO extends BaseDTO{
	private String userName;
    private String firstName;
	
	public UserSearchDTO(){}
	
	public UserSearchDTO(String userName, String firstName){
		this.userName = userName;
		this.firstName = firstName;
	}
	
    public Optional<String> getUserName() {
        return Optional.ofNullable(userName);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	
}