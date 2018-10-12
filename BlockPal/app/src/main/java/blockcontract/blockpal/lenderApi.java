package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.POST;

public interface lenderApi {

    String Base_Url="http://13.233.83.25:7000/";
    @POST("lender/get/{id}")
    Call<lenderPojo> getLender();
}
