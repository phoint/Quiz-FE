package com.fa.training.group01.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fa.training.group01.service.ICloudinaryService;

public class CloudinaryService implements ICloudinaryService {
	@Autowired
	private Cloudinary cloudinary;

	@Override
	public String uploadAudio(MultipartFile file) {
		try {
			Map response = cloudinary.uploader().uploadLarge(file.getBytes(),
					ObjectUtils.asMap("folder", AUDIO_FOLDER, "overwrite", false, "resource_type", "video"));
//			response.forEach((key, value) -> {
//				System.out.println(key + ":" + value);
//			});
			return (String) response.get("secure_url");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String uploadImage(MultipartFile file) {
		try {
			Map response = cloudinary.uploader().upload(file.getBytes(),
					ObjectUtils.asMap("folder", IMAGE_FOLDER, "overwrite", false, "resource_type", "image"));
			return (String) response.get("secure_url");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String uploadVideo(MultipartFile file) {
		try {
			Map response = cloudinary.uploader().uploadLarge(file.getBytes(),
					ObjectUtils.asMap("folder", VIDEO_FOLDER, "overwrite", true, "resource_type", "video"));
			return (String) response.get("secure_url");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
