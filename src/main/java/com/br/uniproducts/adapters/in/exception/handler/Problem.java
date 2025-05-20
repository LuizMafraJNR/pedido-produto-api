package com.br.uniproducts.adapters.in.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem
{
	private Integer status;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private OffsetDateTime dateTime;
	private List<Object> objects;

	@Getter
	@Builder
	public static class Object {
		private String name;
		private String userMessage;
	}
}
