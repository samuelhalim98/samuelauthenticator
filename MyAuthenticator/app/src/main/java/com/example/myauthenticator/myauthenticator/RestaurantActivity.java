package com.example.myauthenticator.myauthenticator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myauthenticator.R;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {
TextView tvrname,tvrphone,tvraddress,tvrloc;
ImageView imgRest;
ListView lvuser;
    Dialog myDialogWindow;
    ArrayList<HashMap<String, String>> userlist;
    String userid,restid,userphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        restid = bundle.getString("restid");
        String rname = bundle.getString("name");
        String rphone = bundle.getString("phone");
        String raddress = bundle.getString("address");
        String rlocation = bundle.getString("location");
        userid = bundle.getString("userid");
        userphone = bundle.getString("userphone");
        initView();
        tvrname.setText(rname);
        tvraddress.setText(raddress);
        tvrphone.setText(rphone);
        tvrloc.setText(rlocation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Picasso.with(this).load("http://uumresearch.com/foodninja/images/"+restid+".jpg")
                .fit().into(imgRest);
 //  .memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE)

        lvuser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showFoodDetail(position);
            }
        });
        loadFoods(restid);

    }

    private void showFoodDetail(int p) {
            myDialogWindow = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_DeviceDefault_Dialog_NoActionBar
            myDialogWindow.setContentView(R.layout.dialog_window);
            myDialogWindow.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView tvfname,tvfprice,tvfquan;
            final ImageView imgfood = myDialogWindow.findViewById(R.id.imageViewFood);
            final Spinner spquan = myDialogWindow.findViewById(R.id.spinner2);
            Button btnorder = myDialogWindow.findViewById(R.id.button2);
            final ImageButton btnfb = myDialogWindow.findViewById(R.id.btnfacebook);
            tvfname= myDialogWindow.findViewById(R.id.textView12);
            tvfprice = myDialogWindow.findViewById(R.id.textView13);
            tvfquan = myDialogWindow.findViewById(R.id.textView14);
            tvfname.setText(userlist.get(p).get("foodname"));
            tvfprice.setText(userlist.get(p).get("foodprice"));
            tvfquan.setText(userlist.get(p).get("foodquantity"));
            final String foodid =(userlist.get(p).get("foodid"));
            final String foodname = userlist.get(p).get("foodname");
            final String foodprice = userlist.get(p).get("foodprice");
            btnorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fquan = spquan.getSelectedItem().toString();
                    dialogOrder(foodid,foodname,fquan,foodprice);
                }
            });

            btnfb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap image = ((BitmapDrawable)imgfood.getDrawable()).getBitmap();
                    SharePhoto photo = new SharePhoto.Builder()
                            .setBitmap(image)
                            .build();
                    SharePhotoContent content = new SharePhotoContent.Builder()
                            .addPhoto(photo)
                            .build();

                    ShareDialog shareDialog = new ShareDialog(RestaurantActivity.this);
                    shareDialog.show(content);
                }
            });
            int quan = Integer.parseInt(userlist.get(p).get("userquantity"));
            List<String> list = new ArrayList<String>();
            for (int i = 1; i<=quan;i++){
                list.add(""+i);
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spquan.setAdapter(dataAdapter);

            Picasso.with(this).load("https://samuelauthenticator.000webhostapp.com/SamuelAuthenticator/php/images/"+userid+".jpg")
                .memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE)
                .fit().into(imgfood);
            myDialogWindow.show();
    }

    private void dialogOrder(final String userid, final String username, final String fquan, final String userprice) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Order "+username
                + " with quantity "+fquan);

        alertDialogBuilder
                .setMessage("Are you sure")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        insertCart(userid,username,fquan,userprice);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void insertCart(final String userid, final String username, final String fquan, final String userprice) {
        class InsertCart extends AsyncTask<Void,Void,String> {

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("user",userid);
                hashMap.put("restid",restid);
                hashMap.put("username",username);
                hashMap.put("quantity",fquan);
                hashMap.put("userprice",userprice);
                hashMap.put("userid",userphone);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("https://samuelauthenticator.000webhostapp.com/SamuelAuthenticator/php/insert_cart.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(RestaurantActivity.this,s, Toast.LENGTH_SHORT).show();
                if (s.equalsIgnoreCase("success")){
                    Toast.makeText(RestaurantActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    myDialogWindow.dismiss();
                    loadFoods(restid);
                }else{
                    Toast.makeText(RestaurantActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

        }
        InsertCart insertCart = new InsertCart();
        insertCart.execute();
    }

    private void loadFoods(final String restid) {
        class LoadFood extends AsyncTask<Void,Void,String> {

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("restid",restid);
                RequestHandler requestHandler = new RequestHandler();
                String s = requestHandler.sendPostRequest("https://samuelauthenticator.000webhostapp.com/SamuelAuthenticator/php/load_foods.php",hashMap);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                userlist.clear();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray userarray = jsonObject.getJSONArray("user");
                    for (int i = 0; i < userarray.length(); i++) {
                        JSONObject c = userarray.getJSONObject(i);
                        String jsid = c.getString("userid");
                        String jsfname = c.getString("username");
                        String jsfprice = c.getString("userprice");
                        String jsquan = c.getString("quantity");
                        HashMap<String,String> foodlisthash = new HashMap<>();
                        foodlisthash.put("userid",jsid);
                        foodlisthash.put("username",jsfname);
                        foodlisthash.put("userprice",jsfprice);
                        foodlisthash.put("userquantity",jsquan);
                        userlist.add(foodlisthash);
                    }
                }catch(JSONException e){}
                ListAdapter adapter = new CustomAdapterFood(
                        RestaurantActivity.this, userlist,
                        R.layout.food_list_rest, new String[]
                        {"foodname","foodprice","foodquantity"}, new int[]
                        {R.id.textView,R.id.textView2,R.id.textView3});
                lvuser
                        .setAdapter(adapter);

            }
        }
        LoadFood loaduser = new LoadFood();
        loaduser.execute();
    }

    private void initView() {
        imgRest = findViewById(R.id.imageView3);
        tvrname = findViewById(R.id.textView6);
        tvrphone = findViewById(R.id.textView7);
        tvraddress = findViewById(R.id.textView8);
        tvrloc = findViewById(R.id.textView9);
        lvuser = findViewById(R.id.listviewfood);
        userlist = new ArrayList<>();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(RestaurantActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userid",userid);
                bundle.putString("phone",userphone);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
