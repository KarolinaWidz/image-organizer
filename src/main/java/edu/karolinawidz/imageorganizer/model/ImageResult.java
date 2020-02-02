package edu.karolinawidz.imageorganizer.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.internal.ws.developer.Serialization;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@JsonSerialize
public class ImageResult {
	@JsonSerialize
	private Long id;
	@JsonSerialize
	private String imagePath;
	@JsonRawValue
	private List<String> tags;

	public ImageResult(Long id, String imagePath, List<String> tags) {
		this.id = id;
		this.imagePath = imagePath;
		this.tags = tags;
	}

}
