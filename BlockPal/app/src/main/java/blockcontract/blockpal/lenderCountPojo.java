package blockcontract.blockpal;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class lenderCountPojo {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("count")
    @Expose
    private String count;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
