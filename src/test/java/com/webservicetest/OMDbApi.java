package com.webservicetest;

import com.models.request.Omdb;
import com.models.response.Movie;
import com.models.response.Search;
import com.utils.InstanceUtils;
import com.utils.StringUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class OMDbApi{


    private RequestSpecification REQUEST;

    private static String URL = "http://omdbapi.com/";

    @Before
    public void setup(){
        RestAssured.baseURI = URL;
    }

    @Test
    public void OmdbApi(){
        Omdb omdb = InstanceUtils.generateInstance(Omdb.class, "test1");

        Search searchResponse = callRequest(omdb, Search.class);
        Movie movie = searchByTitle("Harry Potter and the Sorcerer's Stone", searchResponse);
        Assert.assertNotNull(movie);

        omdb.setI( movie.getImdbID() );
        omdb.setS(null);

        Movie movie2 = callRequest(omdb, Movie.class);
        Assert.assertNotNull(movie2);
        Assert.assertEquals("Harry Potter and the Sorcerer's Stone",movie2.getTitle());
        Assert.assertEquals("2001", movie2.getYear());
        Assert.assertEquals("16 Nov 2001", movie2.getReleased());
    }



    private Movie searchByTitle(String title, Search searchResponse) {
        for (Movie movie : searchResponse.getSearch()) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    private <T> T callRequest(Omdb omdb, Class<T> t){
        ValidatableResponse response = null;
        REQUEST = RestAssured.given().contentType(ContentType.JSON);
        try {
            String url = StringUtils.getUrlString(omdb);
            response = REQUEST
                .when()
                .get("?" + url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return response.extract().response().as(t);
    }

}
