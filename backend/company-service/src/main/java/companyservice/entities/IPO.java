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
    public int exchange_id;

    @Column(length = 15)
    public String price_per_share;

    @Column(length = 10)
    public String total_shares;

    @Column(length = 10)
    public String number_of_shares;

    @Column
    public Date open_date_time;

    @Column(length = 200)
    public String remarks;
}
