package id.ac.binus.my_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Vector;

public class Transaction_Proof_Page extends AppCompatActivity {
    RecyclerView transaction_proof_rv;
    Button back_btn, home_btn;
    TextView subtotal_price, transaction_id, transaction_date;
    String bulan = "";
    String tanggal = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction__proof__page);
        Transaction_Helper transaction_helper = new Transaction_Helper(Transaction_Proof_Page.this);
        Cart_Helper cart_helper = new Cart_Helper(Transaction_Proof_Page.this);
        transaction_proof_rv = findViewById(R.id.transaction_proof_rv);
        back_btn = findViewById(R.id.back_btn);
        home_btn = findViewById(R.id.home_btn);
        subtotal_price = findViewById(R.id.subtotal_price);
        transaction_id = findViewById(R.id.transaction_id);
        transaction_date = findViewById(R.id.transaction_date);

        Intent intent = getIntent();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Transaction_Proof_Page.this);
        int subtotal = intent.getIntExtra("subtotal", -1);
        int user_id = sp.getInt("user_id", -1);

        //TANGGAL
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        String [] tampung =currentDate.split("/");
        Log.e("tess", tampung[0]); //Bulan
        Log.e("tess", tampung[1]); //Tanggal
        Log.e("tess", tampung[2]); //Tahun
        bulan = tampung[0];
        tanggal = tampung[1];
        if (Integer.parseInt(tampung[0])  < 10){
            bulan = "0" + tampung[0];
        }
        if (Integer.parseInt(tampung[1])  < 10){
            tanggal = "0" + tampung[1];
        }

        String inser_date = tanggal + "-" + bulan + "20" + tampung[2];
        Log.e("tess", inser_date);
        //TANGGAL

        //INSERT TRANSACTION TO DB
        transaction_helper.insert_transaction(user_id, subtotal, inser_date);


        Vector<Product_Cart> all_product_cart = cart_helper.getAllProductCart(user_id);


        Vector<Product_Transaction> allTransaction = transaction_helper.getAllTransaction(user_id);


        int lastTransaction = allTransaction.size() - 1;
        Product_Transaction transaction = allTransaction.get(lastTransaction);

        //SET TRANSACTION ID
        transaction_id.setText("SHJD - " + transaction.getTransaction_id());
        //SET TRANSACTION ID

        //SET TRASACTION DATE
        transaction_date.setText("Date : " + transaction.getTransaction_date());
        //SET TRASACTION DATE

        //Setting Recylcer View
        Checkout_Page_Adapter checkout_page_adapter = new Checkout_Page_Adapter(Transaction_Proof_Page.this);
        checkout_page_adapter.setProduct_vector(all_product_cart);

        transaction_proof_rv.setAdapter(checkout_page_adapter);
        transaction_proof_rv.setLayoutManager(new LinearLayoutManager(Transaction_Proof_Page.this));
        //Setting Recycler View

        //SET SUBTOTAL PRICE
        subtotal_price.setText("Rp. " + String.valueOf(subtotal));
        //SET SUBTOTAL PRICE

        //DELETE DB CART
        cart_helper.deleteAll(user_id);
        //DELETE DB CART

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transaction_Proof_Page.this, Checkout_Page.class);
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transaction_Proof_Page.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}