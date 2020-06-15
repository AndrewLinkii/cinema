package cinema.dao.impl;

import cinema.dao.MovieSessionDao;
import cinema.exception.DataProcessingException;
import cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM MovieSession "
                    + "WHERE showTime > :date AND movie.id = :id")
                    .setParameter("date", date.atStartOfDay())
                    .setParameter("id", movieId);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't  to find movie sessions by movie ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            movieSession.setId(movieSessionId);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add movie session to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession getById(Long movieSessionId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MovieSession.class, movieSessionId);
        } catch (HibernateException e) {
            throw new RuntimeException("can't get movie session entity with id: "
                    + movieSessionId);
        }
    }
}
