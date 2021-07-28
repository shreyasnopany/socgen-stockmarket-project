package excelservice.service;

import java.util.List;
import excelservice.entity.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

public interface ExcelDataService {

    @Autowired
    List<StockPrice> getExcelDataAsList();
    int saveExcelData(List<StockPrice> invoices);
}