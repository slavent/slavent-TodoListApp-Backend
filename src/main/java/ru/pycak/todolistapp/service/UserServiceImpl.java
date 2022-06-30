package ru.pycak.todolistapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pycak.todolistapp.dao.UserDAO;
import ru.pycak.todolistapp.dto.CreateUserDTO;
import ru.pycak.todolistapp.dto.UserDTO;
import ru.pycak.todolistapp.entity.User;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public UserDTO getUser(Long id) {
        return convertToDto(userDAO.get(id));
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        User user = userDAO.get(userDTO.getId());
        if (user == null) {
            // TODO: throw new UserDoesNotExistException()
            return;
        }

        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getAvatarUrl() != null) {
            user.setAvatarUrl(userDTO.getAvatarUrl());
        }

        userDAO.save(user);
    }

    @Override
    @Transactional
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        if (userDAO.findByEmail(createUserDTO.getEmail()) != null) {
            // TODO: throw new UserAlreadyExistsException()
            System.out.println("User with this email already exists!");
            return null;
        }
        User user = new User();
        user.setName(createUserDTO.getName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());

        userDAO.save(user);
        return convertToDto(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userDAO.remove(id);
    }

    private UserDTO convertToDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAvatarUrl()
        );
    }
}