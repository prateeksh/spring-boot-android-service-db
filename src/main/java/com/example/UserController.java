package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserRepository repository;

  @Autowired
  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<User> get(@PathVariable("id") Long id) {
    User user = repository.findOne(id);
    if (null == user) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public ResponseEntity<User> update(@RequestBody User user) {
    repository.save(user);
    return get(user.getId());
  }

  @RequestMapping
  public List<User> all() {
    return repository.findAll();
  }
}
