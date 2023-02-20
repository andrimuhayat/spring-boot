package ist.challenge.andri.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ist.challenge.andri.dto.SwapiResponse;
import ist.challenge.andri.dto.request.AuthRequest;
import ist.challenge.andri.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
	ResponseEntity<?> registerUserAccount(AuthRequest authRequest);
	ResponseEntity<?> login(AuthRequest authRequest);
	ResponseEntity<?> editUser(AuthRequest authRequest, String id);
	List<Users> ListUser();
	SwapiResponse ListSwapi(String search) throws JsonProcessingException;

}
