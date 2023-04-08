package com.example.emailproject.apprunner;

import com.example.emailproject.model.Role;
import com.example.emailproject.model.User;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class AppRunner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager;
    @PersistenceContext
    private Session session;

    private final PasswordEncoder passwordEncoder;

    public AppRunner(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {


        Role adminR = new Role("ROLE_ADMIN");
        Role userR  = new Role("ROLE_USER");

        User admin = new User();
        admin.setFirstname("admin");
        admin.setLastname("admin");
        admin.setAge(21);
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("admin"));

        //admin.setRoles(Set.of(Role.adminRole, Role.userRole));


        HashSet<Role> adminSet = new HashSet<>();
        adminSet.add(adminR);
        adminSet.add(userR);

        admin.setRoles(adminSet);


        //session.flush();
        session.save(admin);


        User user = new User();
        user.setFirstname("user");
        user.setLastname("user");
        user.setAge(21);
        user.setEmail("user@gmail.com");
        user.setPassword(passwordEncoder.encode("user"));
        //user.setRoles(Set.of(Role.userRole));


        HashSet<Role> userSet = new HashSet<>();
        userSet.add(userR);

        user.setRoles(userSet);




        //session.flush();
        session.save(user);

        User user1 = new User();
        user1.setFirstname("user1");
        user1.setLastname("user1");
        user1.setAge(21);
        user1.setEmail("user1@gmail.com");
        user1.setPassword(passwordEncoder.encode("user"));
        //user1.setRoles(Set.of(Role.userRole));


        user1.setRoles(adminSet);



        //session.flush();
        session.save(user1);


        Set<Integer> ints = new HashSet<>();
        ints.add(1);
        ints.add(2);
        ints.add(2);
        System.out.println(ints);
    }
}
