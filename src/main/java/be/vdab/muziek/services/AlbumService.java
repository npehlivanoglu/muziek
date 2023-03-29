package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.repositories.AlbumRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findAll() {
        return albumRepository.findAll(Sort.by("naam", "artiest.naam", "jaar"));
    }

    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }

    public List<Album> findByJaar(String jaar) {
        return albumRepository.findByJaar(jaar);
    }
    @Transactional
    public void geeftNieuweScore(long id, int score) {
        var album = albumRepository.findAndLockById(id).orElseThrow(AlbumNietGevondenException::new);
        album.setScore(score);
    }
}
