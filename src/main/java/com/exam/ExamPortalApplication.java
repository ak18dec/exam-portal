package com.exam;

import com.exam.model.admin.Role;
import com.exam.model.admin.User;
import com.exam.repository.rowmappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static com.exam.datafactory.Factory.*;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Exam Portal Server....");
		System.out.println("Populating default master data....");

		List<Role> list = jdbcTemplate.query("Select id, role_name from roles",
				(rs, rowNum) -> new Role( rs.getInt("id"),rs.getString("role_name"))
				);

		getAllRoles(list);

		List<User> users = jdbcTemplate.query("Select * from users", new UserRowMapper());

		getUsers(users);

	}
}
