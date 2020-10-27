package com.example.demo.excel;

import com.sun.javafx.css.Rule;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExportExcelUtil {
    /**
     * 导出excel文件，表头为一维数组表示不用合并单元格
     * @param sheetName
     * @param excelTitle
     * @param dataCollection
     * @param <T>
     * @return
     */
    public static<T> HSSFWorkbook exportExcel(String sheetName, String[] excelTitle, Collection<T> dataCollection) {
        //创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Sheet表格工作空间
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFCellStyle style = workbook.createCellStyle();
        //设置表格默认宽度
        sheet.setDefaultColumnWidth(20);
        //设置表格的表头
        HSSFCell cellHeader;
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelTitle.length; i++) {
            //创建单元格表头
            cellHeader = row.createCell(i);
            cellHeader.setCellValue(new HSSFRichTextString(excelTitle[i]));
        }
        //匹配表头设置单元格的值
        setWorkBookValue(sheet, dataCollection,0, style);

        return workbook;
    }

    /**
     * （根据自定义）把具体数据写入到excel中
     * @param sheet
     * @param dataCollection
     * @param index
     * @param style
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    private static<T> void setWorkBookValue(HSSFSheet sheet,Collection<T> dataCollection, int index,HSSFCellStyle style){
        T t;
        Object[] fields;
        String fieldName;
        String getMethodName;
        HSSFCell cell;
        HSSFRow row;
        Class tClass;
        Method getMethod;
        Object value;
        //遍历集合设置单元格值
        Iterator<T> it = dataCollection.iterator();
        while(it.hasNext()){
            //创建一行单元格
            index ++;
            row = sheet.createRow(index);
            //获取数据
            t = it.next();
            //利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
            fields = t.getClass().getDeclaredFields();
            for(int i = 0; i < fields.length; i++){
                cell = row.createCell(i);
                style.setAlignment(HorizontalAlignment.LEFT);
                cell.setCellStyle(style);
                //利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] newFields = t.getClass().getDeclaredFields();
                fieldName = newFields[i].getName();
                getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    tClass = t.getClass();
                    getMethod = tClass.getMethod(getMethodName, new Class[]{});
                    value = getMethod.invoke(t, new Object[]{});
                    setCellValue(value,cell);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * value格式校验
     */
    private static void setCellValue(Object value,HSSFCell cell){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String textValue = null;
        Pattern pattern = Pattern.compile("");//Pattern pattern = Pattern.compile(RULE);

        Matcher matcher;
        HSSFRichTextString richTextString;
        if (!StringUtils.isEmpty(value)){
            //value进行类型转换
            if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Float) {
                textValue = String.valueOf(value);
                cell.setCellValue(textValue);
            } else if (value instanceof Double) {
                textValue = String.valueOf(value);
                cell.setCellValue(textValue);
            } else if (value instanceof Long) {
                cell.setCellValue((Long) value);
            } else if (value instanceof Boolean) {
                textValue = "是";
                if (!(Boolean) value) {
                    textValue = "否";
                }
            } else if (value instanceof Date) {
                textValue = sdf.format((Date) value);
            } else {
                // 其它数据类型都当作字符串简单处理
                textValue = value.toString();
            }
            if (textValue != null) {
                matcher = pattern.matcher(textValue);
                if (matcher.matches()) {
                    // 是数字当作double处理
                    cell.setCellValue(Double.parseDouble(textValue));
                } else {
                    richTextString = new HSSFRichTextString(textValue);
                    cell.setCellValue(richTextString);
                }
            }
        }
    }

    /**
     * excel 导出文件
     * @param response
     * @param workbook
     * @param fileName
     * @throws IOException
     */
    public static void exportExcelFile(HttpServletResponse response, HSSFWorkbook workbook, String fileName) throws IOException {
        if (workbook != null) {
            response.reset();
            //指定下载的文件名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String filePrefix = sdf.format(new Date());
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            fileName = URLEncoder.encode(filePrefix + "_" + fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            BufferedOutputStream bufferedOutput = null;
            try {
                bufferedOutput = new BufferedOutputStream(response.getOutputStream());
                workbook.write(bufferedOutput);
                bufferedOutput.flush();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (bufferedOutput != null) {
                    try {
                        bufferedOutput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


