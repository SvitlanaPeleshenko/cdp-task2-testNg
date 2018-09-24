package core.data.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

public class TestDataProvider {

    public static final String DATA_PATH = "/src/test/resources/data_files";

    public static String[][] getConfiguredData(Method testMethod) throws IOException {
        return getDataForClassName(testMethod.getDeclaringClass().getName(), testMethod.getName(),
                testMethod.getParameterTypes().length);
    }

    public static String[][] getDataForClassName(String className, String methodName, int numberOfParameters)
            throws IOException {
        return fillData(getDataSheetForClassName(className, methodName), numberOfParameters);
    }

    private static String[][] fillData(Sheet sheet, int numberOfColumns) {
        return fillData(sheet, 1, numberOfColumns);
    }

    private static String[][] fillData(Sheet sheet, int startColumn, int endColumn) {
        if (startColumn == 0) {
            throw new RuntimeException("Start column value should begin from 1.");
        }
        if (endColumn < startColumn) {
            throw new RuntimeException("End column number cannot be less than start column number.");
        }
        int rows = sheet.getPhysicalNumberOfRows();
        System.out.printf("rows" + rows);
        int numberOfParameters = endColumn - startColumn + 1;
        System.out.printf("numberOfParameters" + numberOfParameters);
        String[][] returnValue = new String[rows - 1][numberOfParameters];
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            int jj = 0;
            for (int j = startColumn - 1; j < endColumn; j++) {
                int cellType;
                try {
                    cellType = row.getCell(j).getCellType();
                } catch (NullPointerException ex) {
                    returnValue[i - 1][jj] = "";
                    continue;
                }
                String str;
                FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
                returnValue[i - 1][jj] = handleCell(sheet, cellType, row.getCell(j));
                switch (cellType) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                        returnValue[i - 1][jj] = String.valueOf(row.getCell(j).getDateCellValue());
                    } else {
                        returnValue[i - 1][jj] = String.valueOf(row.getCell(j).getNumericCellValue());
                    }
                    break;

                case Cell.CELL_TYPE_STRING:
                    str = row.getCell(j).getStringCellValue().trim();
                    returnValue[i - 1][jj] = str;
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    evaluator.evaluateInCell(row.getCell(j));
                    str = row.getCell(j).getStringCellValue();
                    returnValue[i - 1][jj] = str;
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    returnValue[i - 1][jj] = Boolean.toString(row.getCell(j).getBooleanCellValue());
                    break;

                default:
                    returnValue[i - 1][jj] = "";
                }
                jj++;
            }
        }
        return returnValue;
    }

    private static String handleCell(Sheet sheet, int cellType, Cell cell) {
        String value;
        String temp;
        FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        switch (cellType) {
        case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                value = String.valueOf(cell.getDateCellValue());
            } else {
                value = String.valueOf(cell.getNumericCellValue());
            }
            break;

        case Cell.CELL_TYPE_STRING:
            temp = cell.getStringCellValue().trim();
            value = temp;
            break;
        case Cell.CELL_TYPE_FORMULA:
            evaluator.evaluateInCell(cell);
            temp = cell.getStringCellValue();
            value = temp;
            break;
        case Cell.CELL_TYPE_BOOLEAN:
            value = Boolean.toString(cell.getBooleanCellValue());
            break;

        default:
            value = "";
        }
        return value;
    }

    public static String getValueByRegexp(String fromString, String regexp, boolean isAssertResult, int groupIndex) {
        String value = null;
        String fromStringAbbreviated = StringUtils.abbreviate(String.valueOf(fromString), 250);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(fromString);
        if (matcher.find()) {
            value = matcher.group(groupIndex);

        }
        if (isAssertResult) {
            if (value == null) {
                if (fromString.length() > 250) {
                    System.out.println("String is too long to display. Value wasn't found by regexp [" + regexp + "]");
                } else {
                    System.out.println("Value in string '" + fromString + "' wasn't found by regexp [" + regexp + "]");
                }
                Assert.fail();
            }
        }
        return value;
    }

    public static String getValueByRegexp(String fromString, String regexp, boolean isAssertResult) {
        return getValueByRegexp(fromString, regexp, isAssertResult, 1);
    }

    public static String getValueByRegexp(String fromString, String regexp) {
        return getValueByRegexp(fromString, regexp, true);
    }

    public static Sheet getDataSheetForClassName(String className, String sheetName) throws IOException {
      String dataFileName =
              String.format("%s%s/%s.xlsx", System.getProperty("user.dir"),DATA_PATH,className).replace("/", File.separator);
      System.out.println("dataFileName "+dataFileName);
        //String dataFileName = "D:\\cdp\\tasks\\designpatterns\\src\\test\\resources\\data_files\\excel\\desktop\\tests.DemoTest.xlsx";
        return getDataSheet(new File(dataFileName), sheetName);
    }

    public static Sheet getDataSheet(File dataFile, String sheetName) throws IOException {
        Workbook workBook = null;
        InputStream inputStream = new FileInputStream(dataFile);

        if (dataFile.getName().toLowerCase().endsWith("xlsx")) {
            workBook = new XSSFWorkbook(inputStream);
        } else if (dataFile.getName().toLowerCase().endsWith("xls")) {
            workBook = new HSSFWorkbook(inputStream);
        }

        inputStream.close();
        if (workBook == null) {
            throw new IOException("Unable to create a Work Book for " + dataFile.getName());
        }
        Sheet sheet = workBook.getSheet(sheetName);
        if (sheet == null) {
            throw new NullPointerException(
                    "Data sheet [" + sheetName + "] not found in data file -> " + dataFile.getName());
        }

        return sheet;
    }

}
