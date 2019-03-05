package poi;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author yrj
 * @date 15:56 2019/3/5
 */
public class ExcelParser {

    /**
     * 解析xls文件
     * @param file
     */
    protected void parseExcel(File file) {

        try {
            FileInputStream inputStream = new FileInputStream(file);
            //获取整个表格文件对象
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            
            //获取所有sheet对象
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext()){
                Sheet sheet = sheetIterator.next();
                parseSheet(sheet);
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析工作簿
     * @param sheet
     */

    protected void parseSheet(Sheet sheet) {
        
        //获取所有Row对象
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()){
            Row row = iterator.next();
            rowAction(row);
            parseRow(row);
        }
    }

    /**
     * 解析每行
     * @param next
     */
    protected void parseRow(Row next) {
        
        //获取所有Cell对象取值
        Iterator<Cell> cellIterator = next.cellIterator();
        while (cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            cell.setCellType(CellType.STRING);
            cellAction(cell);
        }
    }

    /**
     * 行操作
     */
    public HashMap<String,Object> rowAction(Row row){
        return null;
    }

    /**
     * 单元格操作
     */
    public HashMap<String,Object> cellAction(Cell cell){
        return null;
    }
}