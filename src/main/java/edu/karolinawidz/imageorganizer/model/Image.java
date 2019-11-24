package edu.karolinawidz.imageorganizer.model;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="IMAGES")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String imagePath;

	@OneToMany(mappedBy = "image")
	private Set<Tag> tags;

	public Image() {
	}

	public Image(String imagePath, Set<Tag> tags) {
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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}


