package id.ac.binus.my_application;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context ctx;

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }


    int pictureID;

    public void setProduct_vector(Vector<Product> product_vector) {
        this.product_vector = product_vector;
    }

    Vector<Product> product_vector;

    public ProductAdapter(Context ctx) {
        this.ctx = ctx;
    }



    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.home_page_item, parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        //set data
        holder.prod_image_iv.setImageResource(product_vector.get(position).getProduct_image());
        holder.prod_name_txt.setText(product_vector.get(position).getProduct_name());
        holder.prod_rate_txt.setText("Rating: " +  String.valueOf(product_vector.get(position).getProduct_rating()));
        holder.prod_price_txt.setText("Price: " + String.valueOf(product_vector.get(position).getProduct_price()));


//        holder.product_cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //DI SINI BUAT PINDAH HALAMAN KE "PRODUCT DETAIL DAN STORE DATANYA"
////                ctx.startActivity(new Intent(ctx, Product_Detail_Activity.class));
////                Intent intent = new Intent(ctx, Product_Detail_Activity.class);
////                intent.putExtra("product_id_key", product_vector.get(position).getProduct_id());
////                intent.putExtra("product_name_key", product_vector.get(position).getProduct_name());
////                intent.putExtra("min_player__key", product_vector.get(position).getProduct_min_player());
////                intent.putExtra("max_player_key", product_vector.get(position).getProduct_max_player());
////                intent.putExtra("product_price_key", product_vector.get(position).getProduct_price());
////                ctx.startActivity(intent);
////                Toast.makeText(ctx,product_vector.get(position).getProduct_name(), Toast.LENGTH_SHORT).show();
//                //DI SINI BUAT PINDAH HALAMAN KE "PRODUCT DETAIL DAN STORE DATANYA"
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return product_vector.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView prod_image_iv;
        TextView prod_name_txt, prod_rate_txt, prod_price_txt, product_price_txt;
        CardView homepage_cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //get component
            prod_name_txt = itemView.findViewById(R.id.prod_name_txt);
            prod_rate_txt = itemView.findViewById(R.id.prod_rate_txt);
            prod_price_txt = itemView.findViewById(R.id.prod_price_txt);
            homepage_cv = itemView.findViewById(R.id.homepage_cv);
        }
    }
}
