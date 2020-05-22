package cinema.dao;

import cinema.model.MovieSession;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime date);

    MovieSession add(MovieSession session);
}
