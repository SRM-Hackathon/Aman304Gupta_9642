package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Signup_API {
    String BASE_URL="http://localhost//";
    @FormUrlEncoded
    @POST("signUp")
    Call<SignupResponse> getResponse(@Field("username") String username,
                                     @Field("email") String email);
}
