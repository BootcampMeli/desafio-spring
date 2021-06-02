package br.com.socialmeli.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String field;
	private String description;

	public ErrorDetail(String description){
		this.description = description;
	}

}
