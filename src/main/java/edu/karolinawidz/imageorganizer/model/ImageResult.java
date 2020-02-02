package edu.karolinawidz.imageorganizer.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
