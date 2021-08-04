package com.exam.service;

import com.exam.constant.ExceptionConstants;
import com.exam.exception.UserAlreadyExistsException;
import com.exam.exception.UserNotFoundException;
import com.exam.model.admin.User;
import com.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.exam.datafactory.Factory.users;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws Exception {

        Optional<User> local = users.stream().filter(u -> u.getUsername().equals(user.getUsername())).findAny();

        if(local.isPresent()){
            throw new UserAlreadyExistsException(ExceptionConstants.USER_ALREADY_EXISTS+local.get().getUsername());
        }else{
            users.add(user);
        }

        return user;
    }

    public List<User> getAllUsers(){
        return users;
    }

    public User getUser(String username) throws UserNotFoundException {
        Optional<User> usr = users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        if(usr.isPresent()){
            return usr.get();
        }else{
            throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND+username);
        }
    }

    public User getUserById(int id) throws UserNotFoundException {
        User user = userRepository.findById(id);
        if(user == null){
            throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND_FOR_ID+id);
        }
        return user;
    }

    public boolean deleteUser(int userId) {
        return users.removeIf(u->u.getId() == userId);
    }

    public boolean updateUser(String username, User user){
        for (int i=0; i<users.size();i++){
            User u = users.get(i);
            if(u.getUsername().equals(username)){
                users.set(i, user);
                return true;
            }
        }
        return false;
    }
}
