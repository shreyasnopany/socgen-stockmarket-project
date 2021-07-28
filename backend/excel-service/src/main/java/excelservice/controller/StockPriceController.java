package excelservice.controller;

import java.util.List;

import excelservice.entity.StockPrice;
import excelservice.repository.StockPriceRepository;
import excelservice.service.ExcelDataService;
import excelservice.service.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(path = "/excel")
public class StockPriceController {

    @Autowired
    FileUploaderService fileService;

    @Autowired
    ExcelDataService excelDataService;

    @Autowired
    StockPriceRepository repo;

    @GetMapping("/")
    public String index() {
        return "uploadPage";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        fileService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message",
                "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/saveData")
    public String saveExcelData(Model model) {

        List<StockPrice> excelDataAsList = excelDataService.getExcelDataAsList();
        int noOfRecords = excelDataService.saveExcelData(excelDataAsList);
        model.addAttribute("noOfRecords",noOfRecords);
        return "success";
    }
}