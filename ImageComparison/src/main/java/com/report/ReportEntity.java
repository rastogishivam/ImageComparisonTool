package com.report;

public class ReportEntity {

	private String actualImagePath;
	private String exceptedImagePath;
	private String differenceImage;
	private String counter;
	private String screenName;
	private String totalImages;
	private String totalDiffImages;
	private String totalNotDiffImages;
	private String similarityOfImages;

	public ReportEntity(String screenName, String counter, String actualImagePath, String exceptedImagePath, String differenceImage, String similarityOfImages) {
		this.screenName = screenName;
		this.counter = counter;
		this.actualImagePath = actualImagePath;
		this.exceptedImagePath = exceptedImagePath;
		this.differenceImage = differenceImage;
		this.similarityOfImages = similarityOfImages;
	}

	public ReportEntity(String totalImages, String totalDiffImages, String totalNotDiffImages) {
		this.totalImages = totalImages;
		this.totalDiffImages = totalDiffImages;
		this.totalNotDiffImages = totalNotDiffImages;
	}
	
	public String getTotalImages() {
		return totalImages;
	}

	public String getTotalDiffImages() {
		return totalDiffImages;
	}

	public String getTotalNotDiffImages() {
		return totalNotDiffImages;
	}

	public String getCounter() {
		return counter;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getActualImagePath() {
		return actualImagePath;
	}

	public String getExceptedImagePath() {
		return exceptedImagePath;
	}

	public String getDifferenceImage() {
		return differenceImage;
	}

	public String getSimilarityOfImages() {
		return similarityOfImages;
	}

}
