package com.exam.user.repository.resultsetextractors;

import com.exam.user.model.Role;
import com.exam.user.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, User> userMap = new HashMap<>();
        while(rs.next()){
            int userId = rs.getInt("id");
            User user = userMap.get(userId);
            if(user == null){
                user = new User();
                user.setId(userId);
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setProfile(rs.getString("profile"));
                userMap.put(userId, user);
            }
            Set<Role> roles = user.getUserRoles();
            if(roles == null || roles.isEmpty()){
                roles = new HashSet<>();
                user.setUserRoles(roles);
            }
            Role role = new Role();
            role.setId(rs.getInt("roleId"));
            role.setRoleName(rs.getString("roleName"));
            roles.add(role);
        }
        return new ArrayList<>(userMap.values());
    }
}
