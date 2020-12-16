package com.cankarabulut.task;

import com.cankarabulut.task.dto.Film;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;

/**
 * This class responsible for testing search operations through the The Open Movie Database
 *
 * @author cankarabulut
 */
public class FilmSearchTest {

    private final String BASE_URI = "http://www.omdbapi.com/?apikey=7e2cc159&?&";
    private final String FILM_NAME_TO_SEARCH = "Harry+Potter";
    private final String EXPECTED_FILM = "Harry Potter and the Sorcerer's Stone";
    private final String EXPECTED_RELEASE = "16 Nov 2001";
    private final int EXPECTED_YEAR = 2001;

    @Before
    public void setup(){
    }

    @Test
    public void searchFilmAndCheckFields() {
        List<Film> films = searchFilmsByTitle();
        Optional<Film> selectedFilm = getFilmByName(films);
        if(!selectedFilm.isPresent()) {
            fail();
        }
        String filmId = selectedFilm.get().getImdbID();
        Film result = getFilmById(filmId);
        Assert.assertNotNull(result);
        Assert.assertEquals(EXPECTED_FILM, result.getTitle());
        Assert.assertEquals(EXPECTED_YEAR, result.getYear());
        Assert.assertEquals(EXPECTED_RELEASE, result.getReleased());
    }

    /**
     * s parameter is Movie title to search for.
     * @return film lists that match with given film title
     */
    private List<Film> searchFilmsByTitle() {
        return given()
                .when()
                .get(BASE_URI + "s=" + FILM_NAME_TO_SEARCH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().jsonPath().getList("Search", Film.class);
    }

    private Optional<Film> getFilmByName(List<Film> films) {
        return films.stream().filter(film -> film.getTitle().equalsIgnoreCase(EXPECTED_FILM)).findFirst();
    }

    /**
     * i is A valid IMDb ID (e.g. tt1285016)
     * @return film that matches with given imdb id
     */
    private Film getFilmById(String filmId) {
        return given()
                .when()
                .get(BASE_URI + "i=" + filmId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().as(Film.class);
    }
}
