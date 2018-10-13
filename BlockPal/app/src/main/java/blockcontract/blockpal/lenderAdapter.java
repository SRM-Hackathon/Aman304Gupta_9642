package blockcontract.blockpal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class lenderAdapter extends RecyclerView.Adapter<lenderAdapter.ProductViewHolder> {

    private Context mcx;
    private List<lender> lenderList;

    public lenderAdapter(Context mcx, List<lender> lenderList) {
        this.mcx = mcx;
        this.lenderList = lenderList;
    }

    @NonNull
    @Override
    public lenderAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mcx=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(mcx);
        View view=inflater.inflate(R.layout.lender,null);
        lenderAdapter.ProductViewHolder holder=new lenderAdapter.ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull lenderAdapter.ProductViewHolder holder, int position) {
        lender lender=lenderList.get(position);
        holder.textViewUsername.setText(lender.getS1());
        holder.textQuantity.setText(lender.getS2());
//        int price=Integer.parseInt(seller.getQuantity())*5;
        holder.textViewPrice.setText(lender.getS3());


    }

    @Override
    public int getItemCount() {
        return lenderList.size();
    }
    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView textViewUsername,textQuantity,textViewPrice;
        Button buy;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewUsername=itemView.findViewById(R.id.ad);
            textQuantity=itemView.findViewById(R.id.ida);
            textViewPrice=itemView.findViewById(R.id.uname);
            buy=itemView.findViewById(R.id.buy_button);
        }
    }
}
