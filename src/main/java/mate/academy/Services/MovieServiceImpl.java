package mate.academy.Services;
import mate.academy.dao.MovieDao;
import mate.academy.dao.MovieDaoImpl;
import mate.academy.model.Movie;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {
    private MovieDao dao = new MovieDaoImpl();

    @Override
    public Movie add(Movie movie) {
        dao.add(movie);
        return movie;
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movie = dao.get(id);
        return movie.orElse(null);
    }
}
