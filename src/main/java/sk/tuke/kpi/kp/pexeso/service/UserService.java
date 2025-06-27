package sk.tuke.kpi.kp.pexeso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tuke.kpi.kp.pexeso.entity.User;
import sk.tuke.kpi.kp.pexeso.service.JPA.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }
        userRepository.save(new User(username, password));
        return true;
    }

    public Optional<User> loginUser(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password));
    }
}
//package sk.tuke.kpi.kp.pexeso.service;
//
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class UserService {
//
//    // Простая in-memory база пользователей (логин: пароль)
//    private final Map<String, String> users = new HashMap<>();
//
//    public boolean registerUser(String username, String password) {
//        if (users.containsKey(username)) {
//            return false; // уже существует
//        }
//        users.put(username, password);
//        return true;
//    }
//
//    public boolean validateUser(String username, String password) {
//        return users.containsKey(username) && users.get(username).equals(password);
//    }
//}
