package com.brendamarceola.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brendamarceola.workshopmongo.domain.User;
import com.brendamarceola.workshopmongo.dto.UserDTO;
import com.brendamarceola.workshopmongo.repository.UserRepository;
import com.brendamarceola.workshopmongo.services.exception.ObjectNotFoundException;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Object user = repo.findById(id);
		if(user == null) {
			throw new ObjectNotFoundException("Objetor não encontrado");
		}
		return (User) user;
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.delete(null);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
