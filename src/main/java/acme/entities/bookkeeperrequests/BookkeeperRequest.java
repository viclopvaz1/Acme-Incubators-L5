
package acme.entities.bookkeeperrequests;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class BookkeeperRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				firm;
	@NotBlank
	private String				responsabilityStatement;

	private Integer				numBookkeeperRequest;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Authenticated		authenticated;
}
