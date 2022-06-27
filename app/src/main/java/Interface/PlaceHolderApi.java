package Interface;

import java.util.List;

import Beans.AuthenticationRequest;
import Beans.AuthenticationResponse;
import Beans.DNIData;
import Beans.Post;
import Beans.Post2;
import Beans.PostReceived;
import Beans.Profile;
import Beans.RegisterRequest;
import Beans.Review;
import Beans.Rule;
import Beans.SaveProfile;
import Beans.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") int postId);

    @GET("leaseholders/{profileId}/posts")
    Call<List<Post>> getPostsByLeaseholderId(@Path("profileId") int profileId);

    @POST("leaseholders/57/posts/{postId}")
    Call<Post2> createFavoritePost(@Path("postId") int postId);

    @DELETE("leaseholders/22/posts/{postId}")
    Call<ResponseBody> deleteFavoritePost(@Path("postId") int postId);

    @POST("posts/landlords/{landlordId}/posts")
    Call<PostReceived> createPost(@Body Post2 post, @Path("landlordId") int landlordId);

    @POST("users/authenticate")
    Call<User> login(@Body AuthenticationRequest authentication);

    @GET("users/{id}/profiles")
    Call<Profile> getProfile(@Path("id") int id);

    @POST("users")
    Call<AuthenticationResponse> createUser(@Body RegisterRequest request);

    @GET("users")
    Call<List<User>> getUsers();

    @GET("profiles")
    Call<List<Profile>> getProfiles();

    @POST("users/{userId}/plans/{planId}/landlords")
    Call<SaveProfile> createLandlord(@Body SaveProfile profile, @Path("userId") int userId, @Path("planId") int planId);

    @POST("users/{userId}/plans/{planId}/leaseholders")
    Call<SaveProfile> createLeaseholder(@Body SaveProfile profile, @Path("userId") int userId,@Path("planId") int planId);

    @GET("posts/{postId}/reviews")
    Call<List<Review>> getReviews(@Path("postId") int postId);

    @GET("posts/{postId}/rules")
    Call<List<Rule>> getRules(@Path("postId") int postId);

    @GET("landlords/{landlordId}/posts")
    Call<List<Post>> getPostByLandlordId(@Path("landlordId")int landlordId);

    //API de comprobación de DNI
    //https://apiperu.dev/api/dni/72078372?api_token=348c24899e3c784cc082a2e9ee311d9f0d2a612392f24a88ff4005ef8eafaeb1
    @GET("dni/{DNI}?api_token=17ae8f6248c0a15e2d468415ba8735404be49f5df6073ea65a34113f05ac90a0")
    Call<DNIData> getDNIAuth(@Path("DNI") String DNI);
}
