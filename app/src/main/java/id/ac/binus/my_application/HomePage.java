package id.ac.binus.my_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Vector;


public class HomePage extends AppCompatActivity {

    ImageView cart_btn, profile_btn;
    RecyclerView home_page_cv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        cart_btn = findViewById(R.id.cart_btn);
        profile_btn = findViewById(R.id.profile_btn);
        home_page_cv = findViewById(R.id.home_page_cv);

        int temp_1 = getResources().getIdentifier("log001", "drawable", this.getPackageName());
        int temp_2 = getResources().getIdentifier("lg001", "drawable", this.getPackageName());
        int temp_3 = getResources().getIdentifier("sony001", "drawable", this.getPackageName());
        int temp_4 = getResources().getIdentifier("lg002", "drawable", this.getPackageName());
        int temp_5 = getResources().getIdentifier("phil001", "drawable", this.getPackageName());

        Product_Helper product_helper = new Product_Helper(getApplicationContext());
        product_helper.insertProduct("Web Cam C922 Pro", 2569000, 0.0, "Terhubung dengan kejernihan superior setiap kali Anda aktif di saluran seperti Twitch dan YouTube. Streaming apa pun pilihan Anda dalam Full 1080p pada 30 fps atau HD 720p yang super cepat pada 60 fps. Menayangkan dengan mantap menggunakan audio no-drop yang handal, autofocus, dan bidang pandang 78 derajat. Disertai dengan lisensi XSplit premium 3 bulan.", 1.0, "Logitech", "C922 Pro", temp_1);
        product_helper.insertProduct("Smart TV UHD 4K 86 INCH", 65000000, 0.0, "LG UHD TV was made to entertain by taking what you watch to a new level. Whether it's cinema, sports, or games, it delivers realistic images with vivid colour and fine detail in up to four times the definition of Full HD.", 10.0, "LG", "86UN8100PTB", temp_2);
        product_helper.insertProduct("OLED Smart TV 85 INCH", 51000000, 0.0, "Vivid, colourful pictures with sound that surrounds you. See how real entertainment becomes when you combine the colour, contrast and clarity of 4K1 with immersive multidimensional sound. All framed in a luxurious design.", 10.0, "Sony", "KD85X8000H", temp_3);
        product_helper.insertProduct("Washing Machine Front Loading 15KG", 18000000, 0.0, "Mesin Cuci LG 15kg dengan pengering 8kg, AI DD dan TurboWash, ThinQ dengan WiFi.", 30.0, "LG", "F2515RTGV", temp_4);
        product_helper.insertProduct("Air Fryer Essential", 2669000, 0.0, "Menggoreng sehat dengan teknologi Rapid Air.", 6.0, "Philips", "HD9270/90", temp_5);

        Vector<Product> product_list = product_helper.getallproduct();

        //Setting Recylcer View
        ProductAdapter productAdapter = new ProductAdapter(this);
        productAdapter.setProduct_vector(product_list);


        home_page_cv.setAdapter(productAdapter);
        home_page_cv.setLayoutManager(new LinearLayoutManager(this));
        //Setting Recycler View





        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Cart_Page.class);
                startActivity(intent);
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Profile_Page.class);
                startActivity(intent);
            }
        });
    }


}