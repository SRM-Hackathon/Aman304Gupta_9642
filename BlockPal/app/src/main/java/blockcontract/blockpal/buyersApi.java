package blockcontract.blockpal;

import retrofit2.Call;
import retrofit2.http.POST;

public interface buyersApi {

    String Base_Url="http://13.126.150.180:3000/";
    @POST("buyer/read/0")
    Call<BuyersResponse> getResponse();
}
