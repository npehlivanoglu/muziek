package be.vdab.muziek.domain;

import be.vdab.muziek.exceptions.AlbumHeeftDezeTrackAlException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    private long id;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "labelId")
    private Label label;

    private String naam;
    private String jaar;
    private long barcode;
    private int score;
    @ElementCollection
    @CollectionTable(name = "tracks",
            joinColumns = @JoinColumn(name = "albumId"))
    @OrderBy("naam")
    private Set<Track> tracks;

    public Album(long id, Artiest artiest, Label label, long barcode, int score) {
        this.id = id;
        this.artiest = artiest;
        this.label = label;
        this.barcode = barcode;
        this.score = score;
        this.tracks = new LinkedHashSet<>();
    }

    protected Album() {
    }

    public void addTrack(Track track) {
        if (!tracks.add(track)) {
            throw new AlbumHeeftDezeTrackAlException();
        }
    }
    public long getId() {
        return id;
    }

    public Artiest getArtist() {
        return artiest;
    }

    public Label getLabel() {
        return label;
    }

    public String getNaam() {
        return naam;
    }

    public String getJaar() {
        return jaar;
    }

    public long getBarcode() {
        return barcode;
    }

    public int getScore() {
        return score;
    }


    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }


    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album album)) return false;
        return barcode == album.barcode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }
}
