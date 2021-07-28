package companyservice.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Document(collection = "StockPrice")
public class StockPrice {

    @Column(length = 12)
    @Id
    public int id;

    @Column(length = 10)
    public String stock_code;

    @Column(length = 8 )
    public int stock_exchange;

    @Column(length = 7)
    public float current_price;

    @Column
    public LocalDate date;

    @Column
    public LocalTime time;


}