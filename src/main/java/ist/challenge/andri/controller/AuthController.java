package ist.challenge.andri.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import ist.challenge.andri.dto.request.AuthRequest;
import ist.challenge.andri.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {


	private final AuthService AuthService;

	public AuthController(AuthService AuthService) {
		this.AuthService = AuthService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUserAccount(@Valid @RequestBody AuthRequest authRequest) {
		return AuthService.registerUserAccount(authRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
		return AuthService.login(authRequest);
	}

	@PutMapping("/{id}/edit")
	public ResponseEntity<?> editUser(@PathVariable("id") String id, @Valid @RequestBody AuthRequest authRequest) {
		return AuthService.editUser(authRequest,id);
	}

	@GetMapping("/swapi-people")
	public ResponseEntity<?> listUsers(@RequestParam("search") String search) throws JsonProcessingException {
		return ResponseEntity.ok(AuthService.ListSwapi(search));
	}
}
