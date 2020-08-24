package com.cristovantamayo.osworks.api.exceptonhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Problem {
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<ProblemField> fields;
	public static Problem of(int status, OffsetDateTime dataHora, String titulo, List<ProblemField> fields) {
		return new Problem(status, dataHora, titulo, fields);
	}
}