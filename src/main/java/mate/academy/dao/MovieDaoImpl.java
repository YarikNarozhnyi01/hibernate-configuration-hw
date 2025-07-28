package mate.academy.dao;

import java.util.Optional;
import mate.academy.exceptions.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Movie;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Movie add(Movie movie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new DataProcessingException("Can not add movie in MovieDaoImpl", e);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can not get movie in get method",e);
        }
    }
}
