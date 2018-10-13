package blockcontract.blockpal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class amountresponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data1 data1;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data1 getData1() {
        return data1;
    }

    public void setData1(Data1 data) {
        this.data1 = data;
    }
}
