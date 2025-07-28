package mate.academy.services;

import mate.academy.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    Movie get(Long id);
}
