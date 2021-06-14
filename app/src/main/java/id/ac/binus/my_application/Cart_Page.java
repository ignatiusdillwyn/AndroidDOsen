package id.ac.binus.my_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class Cart_Page extends AppCompatActivity {

    RecyclerView cart_page_cv;
    Button back_btn, home_btn, buy_btn;
    TextView dontt_have_transaction_txt;

    Cart_Helper cart_helper = new Cart_Helper(Cart_Page.this);
    int ada_jumlah_item_nol = 0;
    Cart_Page_Adapter cart_page_adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart__page);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        cart_page_cv = findViewById(R.id.cart_page_cv);
        back_btn = findViewById(R.id.back_btn);
        home_btn = findViewById(R.id.home_btn);
        buy_btn = findViewById(R.id.buy_btn);
        dontt_have_transaction_txt = findViewById(R.id.dontt_have_transaction_txt);


        Intent intent = getIntent();
        int user_id = sp.getInt("user_id", -1);
        int product_id = intent.getIntExtra("prod_id", -1);

        Vector<Product_Cart> all_product_cart = cart_helper.getAllProductCart(user_id);//AMBIL SEMUA PRODUK DI CART DARI DB


        if(all_product_cart == null){
            dontt_have_transaction_txt.setText("You don't have any Items in the Cart. Let's Shopping");
        }

        //Setting Recylcer View
        if(all_product_cart != null){
            cart_page_adapter = new Cart_Page_Adapter(this);
            cart_page_adapter.setProduct_vector(all_product_cart);

            cart_page_cv.setAdapter(cart_page_adapter);
            cart_page_cv.setLayoutManager(new LinearLayoutManager(this));


        }
        //Setting Recycler View

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart_Page.this, HomePage.class);
                startActivity(intent);
            }
        });


        //BUTTON HOME
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart_Page.this, HomePage.class);
                startActivity(intent);
            }
        });


        //BUTTON BUY
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart_Page.this, Checkout_Page.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Vector<Product_Cart> all_product_cart = cart_helper.getAllProductCart(sp.getInt("user_id", -1));
                Cart_Page_Adapter cart_page_adapter = new Cart_Page_Adapter(this);
                cart_page_adapter.setProduct_vector(all_product_cart);


                cart_page_cv.setAdapter(cart_page_adapter);
                cart_page_cv.setLayoutManager(new LinearLayoutManager(this));
            }
        }
    }
}