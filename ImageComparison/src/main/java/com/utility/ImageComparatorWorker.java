package com.utility;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.apache.commons.lang3.RandomUtils;

import com.constant.Constants;
import com.report.GenerateReport;
import com.service.FindDiffBWImages;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.comparison.PointsMarkupPolicy;

public class ImageComparatorWorker implements Runnable {

	private FindDiffBWImages bwImages;
	private String pathOfImg1;
	private String pathOfImg2;
	private String outputDirectory;
	private String imagetype;
	private BufferedImage img1;
	private BufferedImage img2;
	private ImageDiffer differ = new ImageDiffer().withColorDistortion(180).withDiffMarkupPolicy(new PointsMarkupPolicy().withDiffColor(Color.RED));

	public ImageComparatorWorker(String pathOfImg1, String pathOfImg2, String outputDirectory, String imagetype) {
		this.pathOfImg1 = pathOfImg1;
		this.pathOfImg2 = pathOfImg2;
		this.outputDirectory = outputDirectory;
		this.imagetype = imagetype;
		findDifferenceBetweenImages();
	}
	
	private FindDiffBWImages findDifferenceBetweenImages() {
		if (bwImages == null) {
			return bwImages = new FindDiffBWImages();
		}else
			return bwImages;
	}
	
	private void compareImages(String imageFilePath1, String imageFilePath2, String outputFolderPath, String imagetype) {
		if (!imageFilePath1.isEmpty() | !imageFilePath2.isEmpty()) {
				img1 = FileUtils.instance().bufferedImage(imageFilePath1);
				img2 = FileUtils.instance().bufferedImage(imageFilePath2);
			if (!bwImages.areImagesEqual(img2, img1)) {
				processmessage();
				BufferedImage writeFinalImage = differ.makeDiff(img2, img1).getMarkedImage();
				FileUtils.instance().writeImage(writeFinalImage, FileUtils.instance().makeDirectory(outputFolderPath), imagetype, imageFilePath1);
				FileUtils.instance().copyFilesOneToOtherLoc(FileUtils.instance().absolutePath(imageFilePath1), Constants.instance().ACTUALIMAGES);
				FileUtils.instance().copyFilesOneToOtherLoc(FileUtils.instance().absolutePath(imageFilePath2), Constants.instance().EXPECTEDIMAGES);
				FileUtils.instance().copyFilesOneToOtherLoc(Constants.instance().OUTPUT_FOLDER.concat(Constants.instance().SINGLE_FORWARD_SLASH) + FileUtils.instance().filename(imageFilePath1), Constants.instance().DIFFERENCEIMAGES);
				String pathOfActualImage = baseDirectory(Constants.instance().ACTUALIMAGES).concat(FileUtils.instance().filename(imageFilePath1));
				String pathOfExceptedImage = baseDirectory(Constants.instance().EXPECTEDIMAGES).concat(FileUtils.instance().filename(imageFilePath1));
				String pathOfDiffImage = baseDirectory(Constants.instance().DIFFERENCEIMAGES).concat(FileUtils.instance().filename(imageFilePath1));
				GenerateReport.instance().generateReportData(FileUtils.instance().imagename(imageFilePath1), String.valueOf(Constants.counter++), pathOfActualImage, pathOfExceptedImage, pathOfDiffImage, bwImages.getSimilarityOfImages(img2, img1));
//				GenerateReport.instance().generateReportData(FileUtils.instance().imagename(imageFilePath1), String.valueOf(Constants.counter++), pathOfActualImage, pathOfExceptedImage, pathOfDiffImage, "92");
				writeFinalImage = null;			
			}
		}
		img1 = null;
		img2 = null;
	}

	public void run() {
		compareImages(pathOfImg1, pathOfImg2, outputDirectory, imagetype);
	}
	
	private void processmessage() {  
        try {  Thread.sleep(100);  } catch (InterruptedException e) { e.printStackTrace(); }  
    } 
	
	private String baseDirectory(String directoryPath) {
		return directoryPath.split(Constants.instance().SINGLE_FORWARD_SLASH)[2].concat(Constants.instance().SINGLE_FORWARD_SLASH).concat(directoryPath.split(Constants.instance().SINGLE_FORWARD_SLASH)[3]).concat(Constants.instance().SINGLE_FORWARD_SLASH);
	}
	 
}