package br.com.resowner.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserResource {
	
	private final UserRepository userRepository;

	@Autowired
	public UserResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getOneUser(@PathVariable("userId") Long userId) {
        
		User user = userRepository.findOne(userId);
       
        if (user == null) {
           // throw new StoryNotFoundException(storyId);
        }
        
        return user;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> checkUser(@RequestParam("login") String login) {
        
		User user = userRepository.findByLogin(login);
       
        if (user == null) {
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(user);
    }
}