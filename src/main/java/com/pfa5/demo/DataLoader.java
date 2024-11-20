package com.pfa5.demo;

import com.pfa5.demo.Entities.Roles;
import com.pfa5.demo.Entities.User;
import com.pfa5.demo.Repo.RoleRepo;
import com.pfa5.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        Roles userRole = new Roles(1,"user");
        roleRepo.save(userRole);
        Roles adminRole = new Roles(2,"admin");
        roleRepo.save(adminRole);
        User user=new User("Amine","Amine@gmail.com",passwordEncoder.encode("Amine"));
        user.addRole(userRole);
        userRepo.save(user);
    }
}
