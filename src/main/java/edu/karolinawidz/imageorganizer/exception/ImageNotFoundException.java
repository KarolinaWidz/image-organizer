package edu.karolinawidz.imageorganizer.exception;


public class ImageNotFoundException extends RuntimeException  {
	public ImageNotFoundException(long id) {
		super("Could not find image: "+id);
	}
}
