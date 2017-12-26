package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.entity.User;
import ua.entity.enums.Role;
import ua.repository.UserRepository;
import ua.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    public void save(User user) {
        user.setName(user.getName().toUpperCase());
        user.setEnabled(true);
        user.setRole(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new NullPointerException("Вже э такий");
        else
            userRepository.save(user);

    }

    @Override
    public User update(User user) {
        if (userRepository.findOne(user.getId()) != null)
            return userRepository.save(user);
        return null;
    }

    @Override
    public List<User> findAlls() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }


    @Override
    public void addMainPhoto(MultipartFile multipartFile, User user) {
//        String path = System.getProperty("catalina.home") + "/resources/"
//                + user.getName() + "/" + multipartFile.getOriginalFilename();
//
//        user.setMainPhoto("resources/" + user.getName() + "/" + multipartFile.getOriginalFilename());
//
//        File file = new File(path);
//
//        try {
//            file.mkdirs();
//            try {
//                FileUtils.cleanDirectory
//                        (new File(System.getProperty("catalina.home") + "/resources/" + user.getName() + "/"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            multipartFile.transferTo(file);
//            userRepository.save(user);
//        } catch (IOException e) {
//            System.out.println("error with file");
//        }

    }

    //головне фото на сторінці юзера
    @Override
    public User findByEmail(String email) {
        return findAlls()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .get();
    }


}
