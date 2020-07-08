
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	public static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	@NotBlank
	private String				rookieGoal;

	@NotBlank
	private String				averageGoal;

	@NotBlank
	private String				expertGoal;

	@Valid
	@NotNull
	private Money				rookieReward;

	@Valid
	@NotNull
	private Money				averageReward;

	@Valid
	@NotNull
	private Money				expertReward;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				deadline;
}
