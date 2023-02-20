package ist.challenge.andri.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {

	@NotEmpty(message = "The full name is required.")
	private String username;

	@NotEmpty(message = "The password is required.")
	private String password;
}
