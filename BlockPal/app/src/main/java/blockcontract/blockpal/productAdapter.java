package blockcontract.blockpal;
/*Adapter for Borrowers*/

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*seller*/

import com.orhanobut.hawk.Hawk;
import com.ramotion.fluidslider.FluidSlider;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ProductViewHolder> {
    private Context mcx;
    private List<borrower> borrowerList;
    int quantity;

    public productAdapter(Context mcx, List<borrower> borrowerList) {
        this.mcx = mcx;
        this.borrowerList = borrowerList;
    }



    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mcx=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(mcx);
        View view=inflater.inflate(R.layout.list_sellers,null);
        ProductViewHolder holder=new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
         borrower borrower=borrowerList.get(position);
        holder.textViewUsername.setText(borrower.getS1());
        holder.textQuantity.setText(borrower.getS2());
//        int price=Integer.parseInt(seller.getQuantity())*5;
        holder.textViewPrice.setText(borrower.getS3());
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(mcx);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_buy);
                Button lend_button=dialog.findViewById(R.id.lend_button);
                lend_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Hawk.init(mcx).build();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(buyApi.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                                .build();
                        buyApi api=retrofit.create(buyApi.class);
                        User user =Hawk.get("user");
                        final int max = Integer.parseInt(borrower.getS6());
                        final int min = 10;
                        final int total = max - min;

                        final FluidSlider slider = dialog.findViewById(R.id.slider_lend);
                        slider.setBeginTrackingListener(new Function0<Unit>() {
                            @Override
                            public Unit invoke() {
                                return Unit.INSTANCE;
                            }
                        });

                        slider.setEndTrackingListener(new Function0<Unit>() {
                            @Override
                            public Unit invoke() {
                                return Unit.INSTANCE;
                            }
                        });

                        // Java 8 lambda
                        slider.setPositionListener(pos -> {
                            final String value = String.valueOf((int) (min + total * pos));
                            slider.setBubbleText(value);
                            quantity= Integer.parseInt(value);
                            return Unit.INSTANCE;
                        });
                        Call<SuccessResponse> call=api.getResponse(borrower.getId(),quantity);
                        call.enqueue(new Callback<SuccessResponse>() {
                            @Override
                            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {

                                SuccessResponse successResponse=response.body();
                                if(successResponse.getSuccess())
                                    Toast.makeText(mcx,"Ordered!!",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                                //now we can do whatever we want with this list

                            }

                            @Override
                            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                                Toast.makeText(mcx, t.getMessage(), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return borrowerList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView textViewUsername,textQuantity,textViewPrice;
        Button buy;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewUsername=itemView.findViewById(R.id.username);
            textQuantity=itemView.findViewById(R.id.quantity);
            textViewPrice=itemView.findViewById(R.id.price);
            buy=itemView.findViewById(R.id.buy_button);
        }
    }
}
