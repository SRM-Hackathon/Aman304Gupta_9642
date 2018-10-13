package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface lenderApi {

    String Base_Url="http://13.233.83.25:7000/";
    @POST("lender/getby/{id}")
    Call<lenderPojo> getLender(@Path("id") String id);
}
