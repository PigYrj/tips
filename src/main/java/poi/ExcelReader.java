package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author yrj
 * @date 15:59 2019/3/5
 */
public class ExcelReader extends ExcelParser{

	private List<String> contextLine;

	public ExcelReader() {
		this.contextLine = new ArrayList<String>();
	}

	@Override
	public HashMap<String,Object> rowAction(Row row) {
		super.rowAction(row);

		HashMap<String,Object> resultMap = new HashMap<String, Object>(1);

		Iterator<Cell> cellIterator = row.cellIterator();
		String text = "";
		while (cellIterator.hasNext()){
			text += cellIterator.next().getStringCellValue() + " ";
		}

		resultMap.put("lineTxt",text);
		contextLine.add(text);
		return resultMap;
	}

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\YRJ\\Desktop\\doc\\txt\\1.txt");
		File excel = new File("C:\\Users\\YRJ\\Desktop\\doc\\excel\\1.xls");
		ExcelReader excelReader = new ExcelReader();
		excelReader.parseExcel(excel);
		FileOutputStream outputStream = new FileOutputStream(file);
		PrintStream ps = new PrintStream(outputStream);
		for (String text : excelReader.contextLine){
			ps.println(text);
		}

		outputStream.close();
		ps.close();
	}
}