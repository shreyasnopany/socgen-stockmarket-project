package companyservice.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection ="Company" )
public class Company {

    @Column(length = 8)
    @Id
    public int id;

    @Column(length = 50)
    public String name;

    @Column(length = 12)
    public float turnover;

    @Column(length = 50)
    public String ceo;

    @Column(length = 8)
    public int sector_id;

    @Column(length = 200)
    public String company_services;

    @Column()
    public List<String> board_of_directors;

    @Column
    public List<Integer> stock_exchanges;
}