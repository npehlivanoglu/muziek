package be.vdab.muziek.controllers;

import be.vdab.muziek.dto.AlbumBeknoptNaamJaar;
import be.vdab.muziek.dto.NieuweAlbumScore;
import be.vdab.muziek.exceptions.ArtiestNietGevondenException;
import be.vdab.muziek.services.ArtiestService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("artiesten")
public class ArtiestController {
    private final ArtiestService artiestService;

    public ArtiestController(ArtiestService artiestService) {
        this.artiestService = artiestService;
    }

    @GetMapping("/{id}/albums")
    Stream<AlbumBeknoptNaamJaar> findAlbumsById(@PathVariable long id) {
        return artiestService.findById(id)
                .orElseThrow(ArtiestNietGevondenException::new)
                .getAlbums().stream().map(AlbumBeknoptNaamJaar::new);
    }


}
