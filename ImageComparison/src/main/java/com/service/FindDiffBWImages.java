package com.service;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.text.DecimalFormat;

import ru.yandex.qatools.ashot.Screenshot;

public class FindDiffBWImages {

	private static DecimalFormat df2 = new DecimalFormat(".####");
	
	public boolean areImagesEqual(Screenshot expected, Screenshot actual) {
		return areImagesEqual(expected.getImage(), actual.getImage());
	}

	public boolean areImagesEqual(BufferedImage expected, BufferedImage actual) {
		return expected.getHeight() == actual.getHeight() && expected.getWidth() == actual.getWidth() && actual.getColorModel().equals(expected.getColorModel()) && areImagesBuffersEqual(expected.getRaster().getDataBuffer(), actual.getRaster().getDataBuffer());
	}

	private boolean areImagesBuffersEqual(DataBuffer expected, DataBuffer actual) {
		return actual.getDataType() == expected.getDataType() && actual.getNumBanks() == expected.getNumBanks()	&& actual.getSize() == expected.getSize() && areImagesBytesEqual(actual, expected);
	}

	private boolean areImagesBytesEqual(DataBuffer expected, DataBuffer actual) {
		for (int bank = 0; bank < expected.getNumBanks(); bank++) {
			for (int i = 0; i < expected.getSize(); i++) {
				if (expected.getElem(bank, i) != actual.getElem(bank, i)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public String getSimilarityOfImages(BufferedImage actual, BufferedImage excepted) {
		return df2.format((100.0000 - getDifferenceInPercent(actual, excepted)));
	}
	
	private double getDifferenceInPercent(BufferedImage img1, BufferedImage img2) {
		double var = 0.0;
		if (img1.getWidth() >= img2.getWidth() & img1.getHeight() <= img2.getHeight() | img1.getWidth() <= img2.getWidth() & img1.getHeight() <= img2.getHeight()) 
			var = makeDiff(img1.getWidth(), img1.getHeight(), img1, img2);	
		else if (img2.getWidth() >= img1.getWidth() & img1.getHeight() >= img2.getHeight() | img1.getWidth() >= img2.getWidth() & img1.getHeight() >= img2.getHeight()) 
			var = makeDiff(img2.getWidth(), img2.getHeight(), img1, img2);
		return var;
	}

	private double makeDiff(int width, int height, BufferedImage img1, BufferedImage img2) {
		long diff = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
			}
		}
		long maxDiff = 3L * 255 * img1.getWidth() * height;
		return 100.0 * diff / maxDiff;
	}

	private int pixelDiff(int rgb1, int rgb2) {
		int r1 = (rgb1 >> 16) & 0xff;
		int g1 = (rgb1 >> 8) & 0xff;
		int b1 = rgb1 & 0xff;
		int r2 = (rgb2 >> 16) & 0xff;
		int g2 = (rgb2 >> 8) & 0xff;
		int b2 = rgb2 & 0xff;
		return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
	}
}
