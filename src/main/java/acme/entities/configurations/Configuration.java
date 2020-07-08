
package acme.entities.configurations;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity {

	public static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				spamWords;

	@DecimalMax(value = "100.0")
	@DecimalMin(value = "2.50")
	private Double				spamThreshold;

	@NotBlank
	private String				sectors;

}
