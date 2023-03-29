package be.vdab.muziek.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    private long id;
    private String naam;

    public Label(String naam) {
        this.id = id;
        this.naam = naam;
    }

    protected Label() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
