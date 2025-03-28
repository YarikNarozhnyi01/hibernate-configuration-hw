package mate.academy.dao;

import java.util.Optional;
import mate.academy.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovieDaoImpl implements MovieDao {
    private Session session = null;
    private Transaction transaction = null;

    @Dao
    @Override
    public Movie add(Movie movie) {
        try {
            session = HibernateUtil.getInstance().openSession();
            transaction = session.beginTransaction();

            transaction.begin();
            session.save(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not add a movie", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }

    @Dao
    @Override
    public Optional<Movie> get(Long id) {
        Optional<Movie> movie;
        try {
            session = HibernateUtil.getInstance().openSession();
            movie = Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can not add a movie", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }
}
