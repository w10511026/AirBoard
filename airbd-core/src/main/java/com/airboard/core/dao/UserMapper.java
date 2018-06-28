package com.airboard.core.dao;


import com.airboard.core.dto.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
	
	List<Users> findAll();

	Users getOne(Long id);

	void insert(Users users);

	void update(Users users);

	void delete(Long id);

}