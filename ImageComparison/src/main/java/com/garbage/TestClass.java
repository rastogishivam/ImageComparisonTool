package com.garbage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import com.constant.Constants;
import com.google.common.io.Files;
import com.service.ImageService;
import com.utility.FileUtils;

public class TestClass {

	public static void main(String[] args) throws IOException {
		String srcFilePath = "E:/Internal_Project/Comparison_Testing/Ashot_Comparison/Image2/1.png";
//		String destFilePath = "E:\\Internal_Project\\Comparison_Testing\\Ashot_Comparison\\Image1";
		System.out.println(Constants.instance().EXTRA_IMG_1.split(Constants.instance().SINGLE_FORWARD_SLASH)[2].concat(Constants.instance().SINGLE_FORWARD_SLASH + Constants.instance().EXTRA_IMG_1.split(Constants.instance().SINGLE_FORWARD_SLASH)[3] + Constants.instance().SINGLE_FORWARD_SLASH));
		
		
//		E:\Internal_Project\Comparison_Testing\Ashot_Comparison\Image2
		
		
		
	/*	File srcFile = new File(srcFilePath);
		File destDir = new File(destFilePath);
		
		File targetFile = new File(destDir, srcFile.getName());
		
		Files.move(srcFile, targetFile);*/
	}
	
}
