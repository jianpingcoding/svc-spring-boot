package org.ganjp.api.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import static org.apache.commons.lang3.StringUtils.defaultString;

@Data
@AllArgsConstructor
public class Header {
	String name;
	String value;

	public String toString() {
		return name + "=[" + defaultString(value) + "]";
	}
}
