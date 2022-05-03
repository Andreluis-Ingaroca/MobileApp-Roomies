package Interface;

import java.util.List;

import Beans.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();
}
