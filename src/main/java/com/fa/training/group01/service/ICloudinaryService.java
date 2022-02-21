package com.fa.training.group01.service;

import org.springframework.web.multipart.MultipartFile;

public interface ICloudinaryService {
	// Maxium file size for images
	long MAX_IMAGE_FILE_SIZE = 10 * 1024 * 1024;
	// Maxium file size for audios and videos
	long MAX_VIDEO_FILE_SIZE = 100 * 1024 * 1024;
	// Folders
	String AUDIO_FOLDER = "audio";
	String IMAGE_FOLDER = "image";
	String VIDEO_FOLDER = "video";

	String uploadAudio(MultipartFile file);

	String uploadImage(MultipartFile file);

	String uploadVideo(MultipartFile file);
}
