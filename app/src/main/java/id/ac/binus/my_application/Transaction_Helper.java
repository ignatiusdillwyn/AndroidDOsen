package id.ac.binus.my_application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class Transaction_Helper {
    Database_Helper helper;

    public Transaction_Helper(Context context){
        helper = new Database_Helper(context);
    }

    public void insert_transaction (int user_id, int transaction_nominal, String transaction_date){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO transactions (user_id, transaction_nominal, transaction_date) " +
                "VALUES ('"+user_id+"', '"+transaction_nominal+"', '"+transaction_date+"')");
        db.close();
        helper.close();
    }

//    public void updateProduct_Cart (int user_id, int product_id, int qty){
//        SQLiteDatabase db = helper.getWritableDatabase();
//        db.execSQL("UPDATE carts " +
//                "SET qty = '"+qty+"' WHERE user_id = '"+user_id+"' AND product_id = '"+product_id+"'");
//        db.close();
//        helper.close();
//    }


//    public Product_Cart get_product_cart (int user_id, int transaction_id){
//        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM transactions  " +
//                "WHERE user_id =? AND transaction_id =?", new String[] {String.valueOf(user_id), String.valueOf(transaction_id)});
//
//        cursor.moveToFirst();
//        Product_Cart product_cart = null;
//        if (cursor.getCount() > 0){
//
//            //GET SEMUA DATA DARI DATABASE
//            int cart_idResult = cursor.getInt(cursor.getColumnIndex("cart_id"));
//            int product_idResult = cursor.getInt(cursor.getColumnIndex("product_id"));
//            String product_nameResult = cursor.getString(cursor.getColumnIndex("product_name"));
//            int  product_priceResult = cursor.getInt(cursor.getColumnIndex("product_price"));
//            double product_ratingResult = cursor.getDouble(cursor.getColumnIndex("product_rating"));
//            String product_descriptionResult = cursor.getString(cursor.getColumnIndex("product_description"));
//            double product_weightResult = cursor.getDouble(cursor.getColumnIndex("product_weight"));
//            String product_brandResult = cursor.getString(cursor.getColumnIndex("product_brand"));
//            String product_typeResult = cursor.getString(cursor.getColumnIndex("product_type"));
//            int product_imageResult = cursor.getInt(cursor.getColumnIndex("product_image"));
//            int product_qtyResult = cursor.getInt(cursor.getColumnIndex("qty"));
//            //GET SEMUA DATA DARI DATABASE
//
//            product_cart = new Product_Cart(cart_idResult, product_idResult,product_nameResult,product_priceResult,product_ratingResult,product_descriptionResult,product_weightResult,product_brandResult,product_typeResult, product_imageResult, product_qtyResult);
//        }
//        cursor.close();
//        db.close();
//        helper.close();
//        return product_cart;
//    }

    public Vector<Product_Transaction> getAllTransaction(int user_id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM transactions  " +
                "WHERE user_id =? ", new String[] {String.valueOf(user_id)});

        cursor.moveToFirst();
        Product_Transaction product_transaction = null;

            Vector<Product_Transaction> all_transaction = null;
            if (cursor.getCount() > 0){
                all_transaction = new Vector<>();
                while (!cursor.isAfterLast()){
                    //GET SEMUA DATA DARI DATABASE
                    int transaction_idResult = cursor.getInt(cursor.getColumnIndex("transaction_id"));
                    int user_idResult = cursor.getInt(cursor.getColumnIndex("user_id"));
                    int  transaction_nominalResult = cursor.getInt(cursor.getColumnIndex("transaction_nominal"));
                    String transaction_dateResult = cursor.getString(cursor.getColumnIndex("transaction_date"));
                    //GET SEMUA DATA DARI DATABASE

                    all_transaction.add(new Product_Transaction(transaction_idResult, user_idResult,transaction_nominalResult,transaction_dateResult));
                    cursor.moveToNext();
                }

            }




        cursor.close();
        db.close();
        helper.close();
        return all_transaction;
    }


}
