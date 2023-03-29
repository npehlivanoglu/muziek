package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Track;
import be.vdab.muziek.dto.NieuweAlbumScore;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.services.AlbumService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.stream.Stream;

@RestController
@RequestMapping("albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    private record AlbumBeknopt(String naam, String artiestNaam, String jaar) {
        AlbumBeknopt(Album album) {
            this(album.getNaam(), album.getArtist().getNaam(), album.getJaar());
        }

    }

    private record AlbumBeknoptMetTracks(String naam, String artiestNaam, String jaar, String labelNaam,
                                         LocalTime totaaltijd, Stream<Track> tracks) {
        AlbumBeknoptMetTracks(Album album) {
            this(album.getNaam(),
                    album.getArtist().getNaam(),
                    album.getJaar(),
                    album.getLabel().getNaam(),
                    album.getTracks().stream().map(Track::getTijd)
                            .reduce((tijd1, tijd2) ->
                                    tijd1.plusHours(tijd2.getHour()).plusMinutes(tijd2.getMinute()).plusSeconds(tijd2.getMinute())).get(),
                    album.getTracks().stream()
            );

        }

    }

    @GetMapping
    Stream<AlbumBeknopt> findAll() {
        return albumService.findAll().stream().map(AlbumBeknopt::new);
    }

    @GetMapping("/{id}")
    AlbumBeknoptMetTracks findAlbumMetTracks(@PathVariable long id) {
        return new AlbumBeknoptMetTracks(albumService.findById(id).orElseThrow(AlbumNietGevondenException::new));
    }

    @GetMapping(params = "jaar")
    Stream<AlbumBeknopt> findByJaar(String jaar) {
        return albumService.findByJaar(jaar).stream().map(AlbumBeknopt::new);
    }

    @PatchMapping("{id}/score")
    void geeftNieuweScore(@PathVariable long id, @Valid NieuweAlbumScore nieuweAlbumScore) {
        albumService.geeftNieuweScore(id, nieuweAlbumScore.score());
    }
}
