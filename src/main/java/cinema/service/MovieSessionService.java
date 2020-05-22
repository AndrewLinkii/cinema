package cinema.service;

import cinema.model.MovieSession;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieSessionService {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime date);
}
