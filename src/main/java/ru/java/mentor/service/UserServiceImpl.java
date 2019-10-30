package ru.java.mentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.mentor.model.Role;
import ru.java.mentor.model.User;
import ru.java.mentor.repositories.RoleRepository;
import ru.java.mentor.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Role userRole;
    private Role adminRole;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAllUsers()  {
        return  userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public boolean addUser(User user) {
        if (notNullDataUser(user) && !isExistLogin(user.getUsername())) {
            initRoleAndEncode(user, null);
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUserAdmin(User user, String access) {
        if (notNullDataUser(user) && !isExistLogin(user.getUsername())) {
            initRoleAndEncode(user, access);
            userRepository.saveAndFlush(user);
        }
        return false;
    }

    @Override
    public boolean updateUser(User user, String access) {
        if (notNullDataUser(user)) {
            initRoleAndEncode(user, access);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        User userDelete = getUserById(user.getId());
        userRepository.delete(userDelete);
        userRepository.flush();
    }

    @Override
    @Transactional
    public void dropTable() {
        userRepository.deleteAll();
    }

    @Override
    @Transactional
    public void createTable()  {
    }

    @Override
    @Transactional
    public boolean isExistLogin(String login) {
        User byLogin = userRepository.findByLogin(login);
        if (byLogin!=null) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean notNullDataLoginPassword(User user) {
        return user.getUsername() != null && !user.getUsername().isEmpty() &&
                user.getPassword() != null && !user.getPassword().isEmpty();
    }

    @Override
    @Transactional
    public boolean notNullDataUser(User user) {
        return user.getUsername() != null && !user.getUsername().isEmpty() &&
                user.getName() != null && !user.getName().isEmpty() &&
                user.getPassword() != null && !user.getPassword().isEmpty() &&
                user.getAddress() != null && !user.getAddress().isEmpty();
    }

    @Override
    @Transactional
    public User editUserView(String edit, User user) {
        User editUser = null;
        if (!edit.isEmpty()) {
            editUser = getUserById(user.getId());
        }
        return editUser;
    }

    private void initRoleAndEncode(User user, String access){
        if (userRole==null) {
            userRole = roleRepository.findById(2);
            adminRole = roleRepository.findById(1);
        }

        if (access==null || access.isEmpty() || access.contains("User")) {
            user.getRole().add(userRole);
        } else if (access.contains("Admin")) {
            user.getRole().add(adminRole);
        } else {
            roleRepository.save(new Role(0, access));
            Role role = roleRepository.findByRole(access);
            user.getRole().add(role);
        }

        if (user.getId()==null) {
            user.setId(0);
        }

        if (user.getPassword().length()<30) {
            String pass = user.getPassword();
            user.setPassword(
                    bCryptPasswordEncoder
                            .encode(pass));
        }
    }
}
