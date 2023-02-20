package ist.challenge.andri.dto;

import ist.challenge.andri.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private String username;
	private String password;


	public UserDto(Users users) {
		this.username = users.getUsername();
		this.password = users.getPassword();
	}
}
