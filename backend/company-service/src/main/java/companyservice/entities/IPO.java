package companyservice.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection ="IPO" )
public class IPO {
    @Column(length = 8)
    @Id
    public int id;

    @Column(length = 8)
    public int company_id;

    @Column(length = 8)
    public int stock_exchange;

    @Column(length = 7, precision = 2)
    public float price_per_share;

    @Column(length = 15)
    public int number_of_shares;

    @Column
    public String open_date_time;

    @Column(length = 200)
    public String remarks;
}
