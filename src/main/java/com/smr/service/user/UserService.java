package com.smr.service.user;

import com.smr.domain.user.User;
import com.smr.service.GenericService;
import com.smr.dto.user.UserSearchDTO;
import java.util.List;

public interface UserService extends GenericService<User>{

    boolean isUserExist(User user);
	User findByUserName(String userName);
	List<User> search(UserSearchDTO dto);
}