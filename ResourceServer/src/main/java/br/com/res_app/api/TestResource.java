package br.com.res_app.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/test")
public class TestResource {

	@PreAuthorize("#oauth2.hasScope('read') and  hasRole('USER')")
	@RequestMapping(value="/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> list() {
		return Arrays.asList("Test", "Test", "Test", "Test");
	}
	
	@PreAuthorize("#oauth2.hasScope('read') and  hasRole('USER')")
	@RequestMapping(value="/list2",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> list2() {
		return Arrays.asList("Test", "Test", "Test", "Test");
	}
}