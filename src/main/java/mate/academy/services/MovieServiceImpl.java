package mate.academy.services;

import java.util.Optional;
import mate.academy.dao.MovieDao;
import mate.academy.lib.Service;
import mate.academy.model.Movie;
import mate.academy.util.HibernateUtil;
import org.hibernate.SessionFactory;

@Service
public class MovieServiceImpl implements MovieService {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movie = movieDao.get(id);
        return movie.orElse(null);

    }
}
