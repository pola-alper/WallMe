package com.alper.pola.andoid.wallme.model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {
 String firstpage = "1";

    @GET("?key=6053326-a38775e380ebc9dac1fd5c260&editors_choice=true&response_group=high_resolution&pretty=true&page=")
    Observable<Example>register(
            @Query("page") int pageIndex

    );

}
