package companyservice.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection ="Company" )
public class Company {

    @Id
    public int id;

    public String name;

    public float turnover;

    public int exchange_id;
}