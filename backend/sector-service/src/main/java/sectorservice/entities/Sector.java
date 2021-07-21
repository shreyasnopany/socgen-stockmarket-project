package sectorservice.entities;

import javax.persistence.Column;
import javax.persistence.Id;

public class Sector {

    @Column(length = 8)
    @Id
    public int id;

    @Column(length = 50)
    public String name;

    @Column(length = 200)
    public String brief;

}
