package edu.karolinawidz.imageorganizer.model;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="images")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String imagePath;

	@OneToMany(mappedBy = "image")
	private List <Tag> tags;

	public Image() {
	}

	public Image(String imagePath, List<Tag> tags) {
		this.imagePath = imagePath;
		this.tags = tags;
	}

	public void addTags(Tag tag) {
		this.tags.add((tag));
		if (tag.getImage() != this) {
			tag.setImage(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List <Tag> getTags() {
		return tags;
	}

	public void setTags(List <Tag> tags) {
		this.tags = tags;
	}
}


