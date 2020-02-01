package edu.karolinawidz.imageorganizer;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

	private Cloudinary cloudinary;

	public ImageUploader(){
		cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dqk1i4lew",
				"api_key", "394395895631517",
				"api_secret","LVmReseF73ohpwNAgxQgQLKHJT8"));

	}


	public String uploadFile(String path){
		File file = new File(path);
		Map uploadResult = null;
		try{
			uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		}catch(IOException e){
			e.printStackTrace();
		}
		return uploadResult.get("url").toString();
	}

}
