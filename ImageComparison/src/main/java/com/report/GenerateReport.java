package com.report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.constant.Constants;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.main.app.JenkinsRunner;

public class GenerateReport {

	private static GenerateReport generateReport;
	private Mustache mustache;
	public ArrayList<ReportEntity> reportData = new ArrayList<ReportEntity>();
	public ArrayList<ReportEntity> reportChartData = new ArrayList<ReportEntity>();
	public String app1Id = JenkinsRunner.prop.getProperty("app1Id");
	public String app2Id = JenkinsRunner.prop.getProperty("app2Id");

	public void generateReport() {
		MustacheFactory mf = new DefaultMustacheFactory();
		if(JenkinsRunner.isMobile())
			mustache = mf.compile(Constants.instance().MUSTECHE_MOBILE_REPORT_PATH);
		else
			mustache = mf.compile(Constants.instance().MUSTECHE_WEB_REPORT_PATH);
		try {
			mustache.execute(new PrintWriter(Constants.instance().COMPARISONREPORT), this).flush();
			reportData.clear();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generateReportData(String screenName, String counter, String path1, String path2, String path3, String similarityOfImages) {
		reportData.add(new ReportEntity(screenName, counter, path1, path2, path3, similarityOfImages));
	}

	public void generateDataforReportChart(String totalImages, String totalDiffImages, String totalNotDiffImages) {
		reportChartData.add(new ReportEntity(totalImages, totalDiffImages, totalNotDiffImages));
	}
	
	public static GenerateReport instance() {
		if (generateReport == null)
			return generateReport = new GenerateReport();
		return generateReport;
	}
}
