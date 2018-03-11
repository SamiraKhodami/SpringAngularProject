package com.smr.controller.user;

import com.smr.domain.user.User;
import com.smr.service.user.UserService;
import com.smr.service.exception.UserAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.smr.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import com.smr.dto.user.UserSearchDTO;

import com.smr.controller.AbstractFormController;

@Service
@RestController("user")
@RequestMapping("/form/")
public class UserController extends AbstractFormController<User>{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }
	public GenericService getGenericService(){
		return userService;
	}
	
    @RequestMapping(
            value = "objects",
            method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
	LOGGER.debug("createUser >>>> User {}",user);
        if (userService.isUserExist(user)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    
    @RequestMapping(
            value = "objects/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<User> updateUserFromDB(@PathVariable("id") long id,
                                                 @RequestBody User user) {
		LOGGER.debug(">>> Updating user with id: " + id);												
        User currentUser = userService.findById(id);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.setUserName(user.getUserName());
        currentUser.setPassword(user.getPassword());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setSurname(user.getSurname());

        userService.update(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(
            value = "users",
            method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> search(@RequestBody UserSearchDTO dto) {
		LOGGER.debug("search >>>> dto {} ",dto);
        List<User> list = userService.search(dto);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }		
	
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return exception.getMessage();
    }
}