package com.fa.training.group01.util;

import com.fa.training.group01.domain_model.Excel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class Exmapper {

    public static final Excel mapEx(Excel model, XSSFRow row ){
        model.setQTitle(row.getCell(1).getStringCellValue().isEmpty()==true?null:row.getCell(1).getStringCellValue());
        model.setQContent(row.getCell(2).getStringCellValue().isEmpty()==true?null:row.getCell(2).getStringCellValue());
        model.setOption1(row.getCell(3).getStringCellValue().isEmpty()==true?null:row.getCell(3).getStringCellValue());
        model.setOption2(row.getCell(4).getStringCellValue().isEmpty()==true?null:row.getCell(4).getStringCellValue());
        model.setOption3(row.getCell(5).getStringCellValue().isEmpty()==true?null:row.getCell(5).getStringCellValue());
        model.setOption4(row.getCell(6).getStringCellValue().isEmpty()==true?null:row.getCell(6).getStringCellValue());
        model.setOption5(row.getCell(7).getStringCellValue().isEmpty()==true?null:row.getCell(7).getStringCellValue());
        model.setCorrect_ans(row.getCell(8).getCellType().equals(CellType.NUMERIC)?String.valueOf(row.getCell(8).getNumericCellValue()):row.getCell(8).getStringCellValue());
        model.setLinkImg(row.getCell(9).getStringCellValue().isEmpty()==true?null:row.getCell(9).getStringCellValue());
        model.setTime(row.getCell(10).getStringCellValue().isEmpty()==true?null:row.getCell(10).getStringCellValue());
        return model;
    }
}
