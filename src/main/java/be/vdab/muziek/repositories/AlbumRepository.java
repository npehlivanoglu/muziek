package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @EntityGraph(attributePaths = {"tracks", "artiest", "label"})
    Optional<Album> findById(long id);

    List<Album> findByJaar(String jaar);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select d from Album d where d.id = :id ")
    Optional<Album> findAndLockById(long id);
}
