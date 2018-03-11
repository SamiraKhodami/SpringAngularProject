package com.smr.service.user;

import com.smr.domain.user.User;
import com.smr.domain.user.UserRepository;
import com.smr.service.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Optional;
import com.smr.annotation.LogExecutionTime;
import com.smr.dto.user.UserSearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Validated
@Transactional
public class UserServiceImpl implements UserService {
	
		private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    private UserRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isUserExist(User user) {
        return Optional.ofNullable(findByUserName(user.getUserName())).isPresent();
    }
	
	@LogExecutionTime
    @Override
    public User save(User user) {
        User existing = repository.findOne(user.getId());
        if (existing != null) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id = %s", user.getId()));
        }
        return repository.save(user);
    }

    @Override
    public User findById(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User update(User user) {
        if (!entityManager.contains(user))
            user = entityManager.merge(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
	
	@Override
	public User findByUserName(String userName){
		return Stream.ofNullable(repository.findByUserName(userName))
		.flatMap(List::stream).findFirst().orElse(null);
	}
	
	@Override
	public List<User> search(UserSearchDTO dto){
		List<User> users = new ArrayList<User>();

		boolean NotEmptyUserName = dto.getUserName().get().length() >0 ; //dto.getUserName().map(String::trim).isPresent();
		boolean NotEmptyFirstName = dto.getFirstName().get().length()> 0; //dto.getFirstName().map(String::trim).isPresent();
		LOGGER.debug("NotEmptyUserName: "+NotEmptyUserName);
		LOGGER.debug("NotEmptyFirstName: "+NotEmptyFirstName);
		
		if(NotEmptyFirstName && NotEmptyUserName)
			users.addAll(repository.findByUserNameAndFirstNameStartingWith(dto.getUserName().get(),dto.getFirstName().get()));
		else if(NotEmptyFirstName)
			users.addAll(repository.findByFirstNameStartingWith(dto.getFirstName().get()));
		else if(NotEmptyUserName)
			users.addAll(repository.findByUserName(dto.getUserName().get()));
		else
			users.addAll(repository.findAll());
		return users;
	}
}