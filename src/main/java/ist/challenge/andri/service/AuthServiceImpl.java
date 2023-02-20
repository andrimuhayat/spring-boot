package ist.challenge.andri.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ist.challenge.andri.dto.SwapiResponse;
import ist.challenge.andri.dto.request.AuthRequest;
import ist.challenge.andri.exception.BadRequestException;
import ist.challenge.andri.exception.ResourceExistException;
import ist.challenge.andri.model.Users;
import ist.challenge.andri.repository.AuthRepository;
import ist.challenge.andri.util.UnirestHttpClient;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	private final AuthRepository authRepository;
	private final UnirestHttpClient unirestHttpClient;
	private final Environment environment;
	private final ObjectMapper objectMapper;


	public AuthServiceImpl(AuthRepository authRepository, UnirestHttpClient unirestHttpClient, Environment environment, ObjectMapper objectMapper) {
		this.authRepository = authRepository;
		this.unirestHttpClient = unirestHttpClient;
		this.environment = environment;
		this.objectMapper = objectMapper;
	}

	@Override
	public ResponseEntity<?> registerUserAccount(AuthRequest authRequest) {
		Optional<Users> userExists = this.authRepository.findByUsername(authRequest.getUsername());
		if (!userExists.isEmpty()) {
			throw new ResourceExistException("User", "Username sudah terpakai", authRequest.getUsername());
		}

		this.authRepository.save(new Users(authRequest));
		return new ResponseEntity<>("sukses", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> login(AuthRequest authRequest) {
		Optional<Users> userExists = this.authRepository.findByUsername(authRequest.getUsername());

		if (authRequest.getUsername().equals("") || authRequest.getPassword().equals("")) {
			throw new BadRequestException("Username dan / atau password kosong");
		}

		if (userExists.isEmpty()) {
			return new ResponseEntity<>("Periksa kembali username / password anda", HttpStatus.UNAUTHORIZED);
		}

		if (!Objects.equals(userExists.get().getPassword(), authRequest.getPassword()) || !Objects.equals(userExists.get().getUsername(), authRequest.getUsername())) {
			return new ResponseEntity<>("Periksa kembali username / password anda", HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>("sukses login", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> editUser(AuthRequest authRequest, String id) {
		Optional<Users> userExists = this.authRepository.findById(Integer.parseInt(id));

		if (userExists.isEmpty()) {
			return new ResponseEntity<>("data tidak ditemukan", HttpStatus.UNAUTHORIZED);
		}

		if (authRequest.getUsername().equals("") || authRequest.getPassword().equals("")) {
			throw new BadRequestException("Username dan / atau password kosong");
		}


		userExists.get().setUsername(authRequest.getUsername());
		userExists.get().setPassword(authRequest.getPassword());

		Users user = userExists.get();
		user.setUsername(authRequest.getUsername());
		user.setPassword(authRequest.getPassword());

		this.authRepository.save(user);
		return new ResponseEntity<>("sukses", HttpStatus.CREATED);
	}

	@Override
	public List<Users> ListUser() {
		return this.authRepository.findAll();
	}

	@Override
	public SwapiResponse ListSwapi(String search) throws JsonProcessingException {
		String url = environment.getProperty("swapi.url");
		Map<String, Object> reqParams = new HashMap<>();
		reqParams.put("search",search);
		HttpResponse<JsonNode> jsonResponse = unirestHttpClient.get(url, null, reqParams);
		String responseJSONString = jsonResponse.getBody().toString();

		return objectMapper.readValue(responseJSONString, new TypeReference<>() {
		});
	}
}
