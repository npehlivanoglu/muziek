package be.vdab.muziek.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlbumHeeftDezeTrackAlException extends RuntimeException {
    public AlbumHeeftDezeTrackAlException() {
        super("Album heeft deze track al.");
    }
}
