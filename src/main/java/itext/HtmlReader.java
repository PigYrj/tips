package itext;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yrj
 * @date 15:45 2019/3/5
 */
public class HtmlReader {

	public static void toPdf(String html, String DEST) throws IOException {
		/*
		 * 常规属性设置
		 */
		ConverterProperties props = new ConverterProperties();
        //字体方案
        FontProgram fontProgram = FontProgramFactory.createFont();
        //转换为pfd中的属性设置对象
        ConverterProperties proper = new ConverterProperties();
        //字体设置，解决中文不显示问题
        FontSet fontSet = new FontSet();
        fontSet.addFont(fontProgram, PdfEncodings.IDENTITY_H);
        FontProvider fontProvider = new FontProvider(fontSet);
        proper.setFontProvider(fontProvider);

        HtmlConverter.convertToPdf(html, new FileOutputStream(DEST), props);

	}

	public static void main(String[] args) throws Exception{
		//文件生成路径
		String dest = "C:\\Users\\YRJ\\Desktop\\pd1\\gen2.pdf";

		//抓取的网页地址
		String path = "https://en.wikipedia.org/wiki/KJHM";
		Document htmlContent = Jsoup.connect(path).get();
		try {
			toPdf(htmlContent.html(), dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}