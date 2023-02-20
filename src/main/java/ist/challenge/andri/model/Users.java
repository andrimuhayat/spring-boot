package ist.challenge.andri.model;

import ist.challenge.andri.dto.request.AuthRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
@Table(name = "users")
public class Users extends PrimaryAudit{

	@Column(unique = true, length = 25, nullable = false)
	private String username;

	@Column(length = 25, nullable = false)
	private String password;


	public Users(AuthRequest authRequest) {
		this.username = authRequest.getUsername();
		this.password = authRequest.getPassword();
	}
}
