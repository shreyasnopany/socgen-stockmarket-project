package excelservice.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import excelservice.entity.StockPrice;
import excelservice.repository.StockPriceRepository;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExcelDataServiceImpl implements ExcelDataService {

    @Value("${app.upload.file:${user.home}}")
    public String EXCEL_FILE_PATH;

    @Autowired
    StockPriceRepository repo;

    Workbook workbook;

    public List<StockPrice> getExcelDataAsList() {

        List<String> list = new ArrayList<String>();

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // Create the Workbook
        try {
            workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }

        // Retrieving the number of sheets in the Workbook
        System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Getting number of columns in the Sheet
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        System.out.println("-------Sheet has '"+noOfColumns+"' columns------");

        // Using for-each loop to iterate over the rows and columns
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }

        // filling excel data and creating list as List<Invoice>
        List<StockPrice> stockPriceList = createList(list, noOfColumns);

        // Closing the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return stockPriceList;
    }

    private List<StockPrice> createList(List<String> excelData, int noOfColumns) {

        ArrayList<StockPrice> stockPriceList = new ArrayList<StockPrice>();

        int i = noOfColumns;
        do {
            StockPrice inv = new StockPrice();

            inv.setStockExchange(excelData.get(i));
            inv.setPricePerShare(Double.valueOf(excelData.get(i + 1)));
            inv.setDate(excelData.get(i + 2));
            inv.setTime(excelData.get(i + 3));

            stockPriceList.add(inv);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return stockPriceList;
    }

    @Override
    public int saveExcelData(List<StockPrice> stockPrices) {
        stockPrices = repo.saveAll(stockPrices);
        return stockPrices.size();
    }
}
