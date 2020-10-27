package com.example.demo.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 　　1.import org.apache.poi.ss.usermodel.Workbook,对应Excel文档；
 * 　　2.import org.apache.poi.hssf.usermodel.HSSFWorkbook，对应xls格式的Excel文档；
 * 　　3.import org.apache.poi.xssf.usermodel.XSSFWorkbook，对应xlsx格式的Excel文档；
 * 　　4.import org.apache.poi.ss.usermodel.Sheet，对应Excel文档中的一个sheet；
 * 　　5.import org.apache.poi.ss.usermodel.Row，对应一个sheet中的一行；
 * 　　6.import org.apache.poi.ss.usermodel.Cell，对应一个单元格。
 */
public class ImportExcelUtil {

    private static final String EXCEL_XLS_SUFFIX = ".xls";

    private static final String EXCEL_XLSX_SUFFIX = ".xlsx";

   static Logger logger= LoggerFactory.getLogger(ImportExcelUtil.class);

    /**
     * 读取Excel数据内容
     * @param filepath 文件路径
     * @param rowIndex    指定行号
     * @param columnIndex 指定列号
     * @return Map 包含单元格数据内容的Map对象
     */
    public List<Map<Integer, Object>> readExcelContent(String filepath, Integer rowIndex, Integer columnIndex) throws Exception {
        //定义返回集合
        List<Map<Integer, Object>> returnList = new LinkedList<>();
        //创建文档
        Workbook wb = null;
        //创建sheet页
        Sheet sheet;
        //定义行
        Row row;
        try {
            //读取文件
            InputStream is = new FileInputStream(filepath);
            //根据后缀判断，创建不同版本的对象
            if (filepath.endsWith(EXCEL_XLS_SUFFIX)) {
                wb = new HSSFWorkbook(is);
            } else if (filepath.endsWith(EXCEL_XLSX_SUFFIX)) {
                wb = new XSSFWorkbook(is);
            }
            //判空
            if (wb == null) {
                throw new Exception("Workbook对象为空！");
            }

            sheet = wb.getSheetAt(0);
            //解析文件总行数、总列数
            int rowNum = rowIndex != null ? rowIndex : sheet.getLastRowNum();
            row = sheet.getRow(0);
            int colNum = columnIndex != null ? columnIndex : row.getLastCellNum();

            //循环列
            for (int colIndex = colNum; colIndex > 0; colIndex--) {
                Cell cell = row.getCell(colIndex);
                if (cell != null && !"".equals(cell.toString())) {
                    colNum = colIndex;
                    break;
                }
            }
            logger.info("have data col:{}", colNum);

            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 0; i <= rowNum; i++) {
                row = sheet.getRow(i);
                int j = 0;

                int size = (int) (colNum / .75f) + 1;
                //存储单元格数据
                Map<Integer, Object> cellValue = new LinkedHashMap<>(size);

                if (row == null) {
                    continue;
                }

                while (j <= colNum) {
                    Cell cell = row.getCell(j);
                    String value = "";
                    //日期单元格需格式化日期
                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date d = cell.getDateCellValue();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                value = formatter.format(d);
                            } else if (cell.toString().contains("E")) {
                                DecimalFormat nf = new DecimalFormat("0");
                                value = nf.format(cell.getNumericCellValue());
                            } else {
                                value = cell.toString().endsWith(".0") ? cell.toString().replace(".0", "") : cell.toString().trim();
                            }
                        } else if (cell.getCellType() == CellType.FORMULA) {
                            value = String.valueOf(cell.getNumericCellValue());
                        } else {
                            value = cell.toString().trim();
                        }
                    }
                    cellValue.put(j, value);
                    j++;
                }
                returnList.add(cellValue);
            }
            wb.close();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
        return returnList;
    }
    public static void main(String[] args) {
        String s= "XML2020-01-01.xml";
        System.out.println(s.endsWith("xml"));
    }
}
