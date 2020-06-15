package cinema.dao.impl;

import cinema.dao.CinemaHallDao;
import cinema.exception.DataProcessingException;
import cinema.model.CinemaHall;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Long cinemaHallId = (Long) session.save(cinemaHall);
            cinemaHall.setId(cinemaHallId);
            transaction.commit();
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cant add cinema hall to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from CinemaHall", CinemaHall.class);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Cant to retrieve all cinema halls. ", e);
        }
    }

    @Override
    public CinemaHall getById(Long cinemaHallId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CinemaHall.class, cinemaHallId);
        } catch (HibernateException e) {
            throw new RuntimeException("can't get cinema hall with id " + cinemaHallId);
        }
    }
}
