package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface lendercreate {
    String Base_Url="http://13.233.83.25:7000/";
    @FormUrlEncoded
    @POST("lender/create")
    Call<SuccessResponse> getResponse(@Field("name") String name,
                                   @Field("linkedInURL") String linkedInURL,
                                   @Field("amount")int amount);
}
