package org.ganjp.api.core.web.response.error;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = ErrorDataBuilder.class)
public interface ErrorDataMixin {}

