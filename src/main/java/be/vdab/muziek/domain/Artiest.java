package be.vdab.muziek.domain;

import be.vdab.muziek.exceptions.ArtistHeeftDezeAlbumAlException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artiesten")
public class Artiest {
    @Id
    private long id;
    private String naam;

    @OneToMany(mappedBy = "artiest",fetch = FetchType.LAZY)
    @OrderBy("naam")
    private Set<Album> albums;

    public Artiest(long id, String naam) {
        this.id = id;
        this.naam = naam;
        this.albums = new LinkedHashSet<>();
    }


    protected Artiest() {
    }

    public void addAlbum(Album album) {
        if (!albums.add(album)) {
            throw new ArtistHeeftDezeAlbumAlException();
        }
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Album> getAlbums() {
        return Collections.unmodifiableSet(albums);
    }

}
