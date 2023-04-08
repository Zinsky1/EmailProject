package com.example.emailproject.dao;

import com.example.emailproject.model.Role;
import com.example.emailproject.model.User;
import org.hibernate.Session;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    @PersistenceContext
    private Session session;

    public UserDaoImp(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setRoles(Set.of(Role.userRole));
        session.flush();
        session.save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = session.createQuery("from User",User.class);
        return query.getResultList();
    }

    @Override
    public User getUser(Long id) {
        return session.find(User.class, id);
    }

    @Override
    public void deleteUser(Long id) {
        User user = session.find(User.class, id);
        session.remove(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setRoles(Set.of(Role.userRole));
        session.flush();
        session.merge(user);
    }

    @Override
    public User findByEmail(String email) {
        return session.createQuery(
                        "SELECT u from User u WHERE u.email = :email", User.class).
                setParameter("email", email).getSingleResult();
    }
}
