package com.cristovantamayo.osworks.api.exceptonhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class ProblemField {
	private String campo;
	private String mensagem;
	public static ProblemField of(String field, String errorMessage) {
		return new ProblemField(field, errorMessage);
	}
}

