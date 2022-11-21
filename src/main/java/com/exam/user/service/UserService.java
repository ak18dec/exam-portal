package com.exam.user.service;

import com.exam.common.constant.ExceptionConstants;
import com.exam.notification.email.service.EmailService;
import com.exam.user.exception.UserAlreadyExistsException;
import com.exam.user.exception.UserNotFoundException;
import com.exam.user.model.User;
import com.exam.otp.repository.OTPRepository;
import com.exam.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPRepository otpRepository;

    public User createUser(User user, int loggedInUserId) throws Exception {

        final boolean userExistWithEmail = userRepository.userExistsByEmail(user.getEmail());

        if(userExistWithEmail){
            throw new UserAlreadyExistsException(ExceptionConstants.USER_ALREADY_EXISTS+user.getEmail());
        }else{
            int newUserId = userRepository.addUser(user, loggedInUserId);
            user.setId(newUserId);
        }

        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        final User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND_FOR_USERNAME +username);
        }
        return user;
    }

    public User getUserById(int id) throws UserNotFoundException {
        final User user = userRepository.findById(id);
        if(user == null){
            throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND_FOR_ID+id);
        }
        return user;
    }

    public boolean deleteUser(int userId) {
        return userRepository.delete(userId);
    }

    public boolean deleteAllUsers() {
        return userRepository.deleteAll();
    }

    public boolean updateUser(int id, User user, int loggedInUserId){
        return userRepository.basicUpdate(id, user, loggedInUserId);
    }

    public boolean updateCredentials(int id, String username, String password, int loggedInUserId){
        return userRepository.updateCredentials(id, username, password, loggedInUserId);
    }

    public boolean updateUserStatus(int id, boolean status, int loggedInUserId){
        return userRepository.updateUserStatus(id, status, loggedInUserId);
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        final User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND_FOR_EMAIL +email);
        }
        return user;
    }
}
