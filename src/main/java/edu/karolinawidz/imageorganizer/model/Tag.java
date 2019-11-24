package edu.karolinawidz.imageorganizer.model;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tags_id")
	private Long id;

	@Column(name = "tag")
	private String tagName;

	@ManyToOne
	@JoinColumn(name ="image_id",nullable = false)
	private Image image;

	public Tag() {
	}

	public Tag(String tagName, Image image) {
		this.tagName = tagName;
		this.image = image;
	}

	public void setImage(Image img){
		this.image = img;
		if(!img.getTags().contains(this)){
			img.getTags().add(this);
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Image getImage() {
		return image;
	}
}
