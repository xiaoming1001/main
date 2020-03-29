package com.study.shiwu.util;    /**
 * @author: wxs
 * @date: 2020/3/21
 */

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zm
 * @date 2020/3/21 14:18
 */
public class ExcelUtils {
    /**
     * 导入
     * @param filepath 文件路径
     * @param startrow 读取的开始行
     * @Result         返回一个二维数组（第一维放的是行，第二维放的是列表）
     * @throws         Exception
     */
    public static String[][] readexcell(String filepath, int startrow, MultipartFile upload) throws Exception{
        //获取原始文件名称
        filepath=upload.getOriginalFilename();
        //设置文件上传的位置
        String path="C:/Users/ASUS/upload/";
        // 创建文件对象
        File file = new File(path);
        //判断文件是否存在
        if (!file.exists()){
            file.mkdirs();
        }
        //设置文件名唯一值
        String uuid= UUID.randomUUID().toString().replace("_","");
        String filepath1=uuid+"_"+filepath;
        System.out.println("唯一文件名字是："+filepath1);
        //新路径
        String path1="C:/Users/ASUS/upload/"+filepath1;
        System.out.println("新的文件路径是："+path1);
        //上传新的位置
        upload.transferTo(new File(path1));
        //获取工作表sheet（文件存在的时候调用：getSheet(filepath) 来取得工作表）
        Sheet sheet = getSheet(path1);
        System.out.println("取得工作表的名称："+sheet.getSheetName()+":-----"+"这里获取需要的工作表是："+sheet);
        // 得到总行数(+1 第一行是表头，不是数据)
        int rowNum = sheet.getLastRowNum()+1;
        System.out.println("得到总行数："+rowNum);
        // 获取第一行(从下标0开始)的整列数据（横向）
        Row row = sheet.getRow(0);
        System.out.println("根据第一行的整列数据："+row.getCell(0)+"--"+row.getCell(1)+"--"+row.getCell(2));
        //获取总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("获取到的总列数是："+colNum);
        //根据行列创建二维数组[行数据][列数据]     [rowNum-startrow]总行数-起始行
        String[][] content = new String[rowNum-startrow][colNum];
        //用来储存每个单元格的值
        String[] cols = null;
        //通过循环，给二维数组赋值
        for (int i = startrow; i < rowNum; i++) {
            row = sheet.getRow(i);
            cols = new String[colNum];
            for (int j = 0; j < colNum; j++) {
                //获取每个单元格的值（调用：getCellValue(row.getCell(j) 来取得每一个单元格的值）
                cols[j] = getCellValue(row.getCell(j));
                //把单元格的值存入二维数组
                content[i - startrow][j] =cols[j];
            }
        }
        return content;
    }
    /**
     * 根据表名获取第一个sheet
     * @return 2003-HSSFWorkbook  2007-XSSFWorkbook
     * @throws Exception
     */
    public static Sheet getSheet(String file) throws Exception {
        //通过截取，取得文件后缀
        String extension = file.lastIndexOf(".") == -1 ? "" : file.substring(file.lastIndexOf("."));
        //创建输入流（从文件--->内存读取）
        InputStream is = new FileInputStream(file);
        //通过文件后缀名称获取工作簿
        if (".xls".equals(extension)) {   //2003版本使用此文件后缀
            //获取工作薄
            POIFSFileSystem fs = new POIFSFileSystem(is);
            return new HSSFWorkbook(fs).getSheetAt(0);

        } else if (".xlsx".equals(extension) || ".xlsm".equals(extension)) {   //2007使用此文件后缀
            return new XSSFWorkbook(is).getSheetAt(0);
        } else {
            //当文件后缀无法识别
            throw new IOException("文件（" + file + "）,无法识别！");
        }
    }


    /**
     * 功能:获取单元格的值
     */
    public static String getCellValue(Cell cell) {
        //创建一个空的对象
        Object result = "";

        if (cell != null) {
            //循环单元格的值   cell.getCellType()默认类型是int
            switch (cell.getCellType()) {
                //字符串型
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                    //数值型
                case Cell.CELL_TYPE_NUMERIC:
                    if(HSSFDateUtil.isCellDateFormatted(cell)){             // 在excel里,日期也是数字,在此要进行判断
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = cell.getDateCellValue();
                        result =  sdf.format(date);
                    }else{
                        DecimalFormat df=new DecimalFormat("#");     //普通数值型
                        result=df.format(cell.getNumericCellValue());
                    }
                    break;
                    //布尔型
                case Cell.CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                    //公式型
                case Cell.CELL_TYPE_FORMULA:
                    result = cell.getCellFormula();
                    break;
                    //错误
                case Cell.CELL_TYPE_ERROR:
                    result = cell.getErrorCellValue();
                    break;
                    //空值
                case Cell.CELL_TYPE_BLANK:
                    break;
                    //其他，没有任何数据也必须写
                default:
                    break;
            }
        }
        return result.toString();
    }








    /**
     * 导出1---写死了导出路径
     * 根据传入List数据集合导出Excel表格 生成本地excel
     * @param file           （输出流路径）D://daochu.xls
     * @param list            任何对象类型的list（数据库直接查询出的数据)
     * @param columnNames     （表头名称）(姓名、性别、年龄)
     * @param columns        （表头对应的列名）（name,sex,age）注意顺序
     * @param sheetName       （sheet名称）
     */
    public static void exportExcelByList(String file, List list,String[] columnNames, String[] columns, String sheetName) {
        OutputStream fos  =null;
        try {
            //获取输出流对象
            fos= new FileOutputStream(file);
            //创建工作薄HSSFWorkbook
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建表单sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            //创建样式对象
            HSSFCellStyle style = wb.createCellStyle(); // 样式对象
            //style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
            //创建行--表头
            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i < columnNames.length; i++) {
                //创建列、单元格
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(columnNames[i]);
                cell.setCellStyle(style);
            }
            //创建数据列
            for (int i = 0; i < list.size(); i++) {
                Object object = list.get(i);
                System.out.println("得到的数据下标I："+object);
                //创建行--数据
                HSSFRow listRow = sheet.createRow(i + 1);
                //循环列字段数组
                for (int j = 0; j < columns.length; j++) {
                    //创建列
                    HSSFCell listCell = listRow.createCell(j);
                    //根据反射调用方法
                    Method m = object.getClass().getMethod("get" + upperStr(columns[j]));
                    System.out.println("得到的M的值是："+m);
                    System.out.println(m.invoke(object));
                    String value =String.valueOf( m.invoke(object));
                    if (value != null) {
                        listCell.setCellValue(value);
                        listCell.setCellStyle(style);
                    } else {
                        listCell.setCellValue("");
                        listCell.setCellStyle(style);
                    }
                    sheet.autoSizeColumn(j+1, true);//自适应，从1开始
                }
            }
            //把工作薄写入到输出流
            wb.write(fos);
            System.out.println("生成excel成功："+file);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 导出2
     * 根据传入List数据集合导出Excel表格 返回页面选择保存路径的excel
     * @param response      （响应页面）
     * @param list          查询的一个数据集合列表
     * @param columnNames   导出后的excel文件的表头
     * @param columns       对应数据库里的属性名
     * @param sheetName     工作簿的名字
     * @param filename      导出的文件名
     */
    public static void exportExcel(HttpServletResponse response, List list, String[] columnNames, String[] columns, String sheetName, String filename) {
        OutputStream fos = null;
        try {
            //响应输出流，让用户自己选择保存路径
            fos = getOutputStream(response, filename);
            System.out.println("输出fos:"+fos);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            // 样式对象
            HSSFCellStyle style = wb.createCellStyle();
            // 水平
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   垂直

            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i < columnNames.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(columnNames[i]);
                cell.setCellStyle(style);
            }
            for (int i = 0; i < list.size(); i++) {
                HSSFRow listRow = sheet.createRow(i + 1);
                Object object = list.get(i);
                for (int j = 0; j < columns.length; j++) {
                    HSSFCell listCell = listRow.createCell(j);
                    Method m = object.getClass().getMethod("get" + upperStr(columns[j]));
                    Object value =  m.invoke(object);
                    if (value != null) {
                        listCell.setCellValue(value + "");
                        listCell.setCellStyle(style);
                    } else {
                        listCell.setCellValue("");
                        listCell.setCellStyle(style);
                    }
                    //自适应，从1开始
                    sheet.autoSizeColumn(j+1, true);
                }
            }
            wb.write(fos);
            System.out.println("导出文件成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据response获取输出流
     * @param response
     * @param filename 生成文件的文件名称
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static OutputStream getOutputStream(HttpServletResponse response, String filename) throws Exception {
        OutputStream fos = null;
        response.setCharacterEncoding("UTF-8");
        response.reset();//清除缓存
        response.setContentType("application/force-download");// 设置强制下载不打开
        byte[] s=filename.getBytes("UTF-8");
        //new String((filename).getBytes("UTF-8"), "iso8859-1")
        try {
            response.addHeader("Content-Disposition", "attachment;filename="+ s+ ".xls");
            fos = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fos;
    }
    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private static String upperStr(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
    public static void exportBigData(HttpServletResponse response, List list, String[] columnNames, String[] columns, String sheetName, String filename){

        OutputStream os = null;
        try {
            response.setContentType("application/force-download"); // 设置下载类型
            response.setHeader("Content-Disposition","attachment;filename=" + filename); // 设置文件的名称
            os = response.getOutputStream(); // 输出流
            SXSSFWorkbook wb = new SXSSFWorkbook(1000);//内存中保留 1000 条数据，以免内存溢出，其余写入 硬盘
            //获得该工作区的第一个sheet
            Sheet sheet1 = wb.createSheet(sheetName);
            int excelRow = 0;
            //标题行
            Row titleRow = (Row) sheet1.createRow(excelRow++);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = titleRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            if (list!= null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    //明细行
                    Row contentRow = (Row) sheet1.createRow(excelRow++);
                    List<String> reParam = (List<String>) list.get(i);
                    for (int j = 0; j < reParam.size(); j++) {
                        Cell cell = contentRow.createCell(j);
                        cell.setCellValue(reParam.get(j));
                    }
                }
            }
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } // 关闭输出流
        }
    }

}
