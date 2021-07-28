package sectorservice.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Document(collection = "Exchange")
public class Sector {

    @Column(length = 8)
    @Id
    public int id;

    @Column(length = 50)
    public String name;

    @Column(length = 200)
    public String brief;

}
