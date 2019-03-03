package com.acatim.acatimver1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.model.UserRole;

@Repository
@Transactional
public class RoleDAO {
	@Autowired
    private EntityManager entityManager;
 
    public List<String> getRoleNames(int userId) {
        String sql = "Select ur.Role.role_name from " + UserRole.class.getName() + " ur " //
                + " where ur.User.userId = :userId ";
 
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
