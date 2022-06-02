package com.bulk.imports.helper;

import com.bulk.imports.persistance.entity.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

public class BulkHelper {

   // public static String TYPE = "application/vnd.openxmlformats-officedocument.speadsheetml.sheet";
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Id", "FirstName", "LastName", "Email", "Department"};
    static String SHEET = "Employees";

    public static boolean hasExcelFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<Employee> excelToEmployeeList (InputStream is){
        try {
//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheet(SHEET);
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            List<Employee> employeeList = new ArrayList<Employee>();
            int rowNumber = 0;
            while (rows.hasNext()){
                Row currentRow = rows.next();
                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Employee employee = new Employee();
                int cellIdx = 0;
                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx){
                        case 0:
                            employee.setId((int) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            employee.setFirstName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            employee.setLastName(currentCell.getStringCellValue());
                            break;
                        case 3:
                            employee.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            employee.setDepartment(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                employeeList.add(employee);
            }
            workbook.close();
            return employeeList;
        } catch (IOException e){
            throw new RuntimeException("fail to parse Excel File: " + e.getMessage());
        }
    }
}
