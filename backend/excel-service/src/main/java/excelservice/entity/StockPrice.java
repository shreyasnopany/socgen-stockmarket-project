package excelservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class StockPrice {

    @Id
    @GeneratedValue
    private int id;
    private String stockExchange;
    private Double pricePerShare;
    private String date;
    private String time;

    public StockPrice() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStockExchange() {
        return stockExchange;
    }
    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }
    public Double getPricePerShare() {
        return pricePerShare;
    }
    public void setPricePerShare(Double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public StockPrice(int id, String stockExchange, Double pricePerShare, String date, String time) {
        super();
        this.id = id;
        this.stockExchange = stockExchange;
        this.pricePerShare = pricePerShare;
        this.date = date;
        this.time = time;
    }
}