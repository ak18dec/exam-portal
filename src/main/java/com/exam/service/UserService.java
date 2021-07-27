package com.exam.service;

import com.exam.constant.ExceptionConstants;
import com.exam.exception.UserAlreadyExistsException;
import com.exam.exception.UserNotFoundException;
import com.exam.model.admin.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.exam.datafactory.Factory.users;

@Service
public class UserService {

    public User createUser(User user) throws Exception {

        Optional<User> local = users.stream().filter(u -> u.getUsername().equals(user.getUsername())).findAny();

        if(local.isPresent()){
            throw new UserAlreadyExistsException(ExceptionConstants.USER_ALREADY_EXISTS+local.get().getUsername());
        }else{
            user.setId(users.size()+1L);
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

    public boolean deleteUser(Long userId) {
        return users.removeIf(u->u.getId().equals(userId));
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
