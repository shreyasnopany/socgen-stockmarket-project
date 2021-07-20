package stockexchange.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Document(collection = "Exchange")
public class Exchange {

    @Column(length = 8)
    @Id
    public int id;

    @Column(length = 30)
    public String name;

    @Column(length = 200)
    public String brief;

    @Column(length = 200)
    public String remarks;

}