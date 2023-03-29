package be.vdab.muziek.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArtistHeeftDezeAlbumAlException extends RuntimeException {
    public ArtistHeeftDezeAlbumAlException() {
        super("Artist heeft deze album al.");
    }
}
