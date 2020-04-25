package com.arya.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.arya.security.jwt.model.Response;
import com.arya.security.jwt.model.User;
import com.arya.security.jwt.model.UserModel;
import com.arya.security.jwt.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public Response<User> saveUser(@RequestBody UserModel user) {
		return new Response<>(HttpStatus.OK.value(), "User saved successfully...!", userService.save(user));
	}

	@GetMapping
	public Response<List<User>> listUser() {
		return new Response<>(HttpStatus.OK.value(), "User list fetched successfully...!", userService.findAll());
	}

	@GetMapping("/{id}")
	public Response<User> getOne(@PathVariable int id) {
		return new Response<>(HttpStatus.OK.value(), "User fetched successfully...!", userService.findById(id));
	}

	@PutMapping("/{id}")
	public Response<UserModel> update(@RequestBody UserModel userModel) {
		return new Response<>(HttpStatus.OK.value(), "User updated successfully...!", userService.update(userModel));
	}

	@DeleteMapping("/{id}")
	public Response<Void> delete(@PathVariable int id) {
		userService.delete(id);
		return new Response<>(HttpStatus.OK.value(), "User deleted successfully...!", null);
	}

}
