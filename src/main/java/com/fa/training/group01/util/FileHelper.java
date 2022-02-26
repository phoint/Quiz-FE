package com.fa.training.group01.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileHelper {
	public static final File convertMultiPartToFile(MultipartFile file) {
		try {
			if (file != null && !file.isEmpty()) {
				File convFile = new File(file.getOriginalFilename());
				FileOutputStream fos = new FileOutputStream(convFile);
				fos.write(file.getBytes());
				fos.close();
				return convFile;
			}
			return null;
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

	public static final FileSystemResource convertToFileSystemResource(MultipartFile multipartFile) {
		try {
			if (multipartFile != null && !multipartFile.isEmpty()) {
				File file = convertMultiPartToFile(multipartFile);
				return new FileSystemResource(file);
			}
			return null;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

//	public static final ByteArrayResource convertToByteArrayResource(MultipartFile multipartFile) {
//		try {
//			return new ByteArrayResource();
//		} catch (Exception e) {
//			System.err.println(e);
//			return new ByteArrayResource(new byte[0]);
//		}
//	}
}
