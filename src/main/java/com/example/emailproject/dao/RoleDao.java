package com.example.emailproject.dao;

import com.example.emailproject.model.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao {

    @PersistenceContext
    private Session session;


    public List<Role> allRoles() {
        return session.createQuery("from Role", Role.class).getResultList();
    }
}
