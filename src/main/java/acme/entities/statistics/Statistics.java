
package acme.entities.statistics;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Statistics implements Serializable {

	public static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				nombre;

	private Double				valor;

}
