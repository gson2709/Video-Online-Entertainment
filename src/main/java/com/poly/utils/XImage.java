package com.poly.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class XImage {
	public static void save(File src) {
		File dst = new File("D:\\26_PS28791_Son\\Java\\Java4\\ASM\\src\\main\\webapp\\images", src.getName());
        if (!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}
