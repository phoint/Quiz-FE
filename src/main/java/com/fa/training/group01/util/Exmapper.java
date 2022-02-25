package com.fa.training.group01.util;

import com.fa.training.group01.domain_model.Ex.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

public class Exmapper {

        public static final QuizEx mapEx(XSSFWorkbook workbook){
        XSSFSheet Quiz = workbook.getSheetAt(0);
        XSSFSheet Quest = workbook.getSheetAt(1);
        QuizEx quizEx = new QuizEx();
        List<PartEx> parts = new ArrayList<>();
        List<SectionEx> sections = new ArrayList<>();

        for(int i = 1;i<Quest.getPhysicalNumberOfRows() ;i++) {
            XSSFRow row = Quest.getRow(i);
            //Quest
            List<QuestionEx> questions = new ArrayList<>();
            List<AnswerEx> answers = new ArrayList<>();
            answers.add(new AnswerEx(row.getCell(3).getStringCellValue(),false));
            answers.add(new AnswerEx(row.getCell(4).getStringCellValue(),false));
            answers.add(new AnswerEx(row.getCell(5).getStringCellValue(),false));
            answers.add(new AnswerEx(row.getCell(6).getStringCellValue(),false));
            answers.add(new AnswerEx(row.getCell(7).getStringCellValue().isEmpty()==true?null:
                    row.getCell(7).getStringCellValue(),false));
            questions.add(new QuestionEx(row.getCell(0).getStringCellValue(),
                    row.getCell(1).getStringCellValue(),
                    (int)row.getCell(2).getNumericCellValue(),answers));
            //Section
            for(int j = 1;j<Quiz.getPhysicalNumberOfRows() ;j++) {
                XSSFRow rowq = Quiz.getRow(j);
                sections.add(new SectionEx(rowq.getCell(3).getStringCellValue(),questions));
            }
        }
        //Part
        for(int j = 1;j<Quiz.getPhysicalNumberOfRows();j++) {
            XSSFRow rowq = Quiz.getRow(j);
            parts.add(new PartEx(rowq.getCell(2).getStringCellValue(),sections));
            quizEx.setTitle(rowq.getCell(0).getStringCellValue());
            quizEx.setContent(rowq.getCell(1).getStringCellValue());
            quizEx.setParts(parts);
        }

       return quizEx;
    }
}
