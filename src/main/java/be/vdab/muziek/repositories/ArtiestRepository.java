package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Artiest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtiestRepository extends JpaRepository<Artiest,Long> {
    @EntityGraph(attributePaths = "albums")
    Optional<Artiest> findById(long id);
}
