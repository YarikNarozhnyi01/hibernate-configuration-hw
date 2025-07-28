package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.services.MovieService;

public class Main {
    public static void main(String[] args) {
        Injector injector = Injector.getInstance("hibernate-configuration-hw");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

    }
}
