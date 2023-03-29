package be.vdab.muziek.dto;

import be.vdab.muziek.domain.Album;

public record AlbumBeknoptNaamJaar(String naam, String jaar) {
    public AlbumBeknoptNaamJaar(Album album) {
        this(album.getNaam(), album.getJaar());
    }

}
