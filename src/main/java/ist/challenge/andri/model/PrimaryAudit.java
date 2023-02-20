package ist.challenge.andri.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"})
public class PrimaryAudit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id",unique = true, nullable = false)
	private Integer id;

	@JsonIgnore
	@Column(name = "created_at")
	private Instant createdAt;

	@JsonIgnore
	@Column(name = "updated_at")
	private Instant updatedAt;

	public PrimaryAudit() {
		this.createdAt = Instant.now();
		this.updatedAt = null;
	}
}
