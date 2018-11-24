package lynx.ancha.starwars.data.rest;

import io.reactivex.Observable;

import lynx.ancha.starwars.data.model.rest.RawResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiPeoples {

    @GET("/api/people/?format=json")
    Observable<RawResult> getAllPeoples(
            @Query("page") Integer page
    );

}
