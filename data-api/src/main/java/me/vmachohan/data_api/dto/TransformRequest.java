package me.vmachohan.data_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransformRequest(@JsonProperty("text") String text) {
}
