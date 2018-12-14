package com.innobot.myrecyclerwithradiobutton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity  implements  TemplateSelectionListener{

    private RecyclerView offerRecyclerView;
    private TextView offers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offers = (TextView)findViewById(R.id.offers);
        Intent intent = getIntent();
        if (null != intent) {
            String stringData= intent.getStringExtra("OfferName");
            offers.setText(stringData);
        }

        offerRecyclerView = (RecyclerView) findViewById(R.id.offers_lst);

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        offerRecyclerView.setLayoutManager(recyclerLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(offerRecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        offerRecyclerView.addItemDecoration(dividerItemDecoration);


        OffersRecyclerViewAdapter recyclerViewAdapter = new
                OffersRecyclerViewAdapter(getBrands(),MainActivity.this,this);
        offerRecyclerView.setAdapter(recyclerViewAdapter);

    }

    private List<OffersModel> getBrands(){
        List<OffersModel> modelList = new ArrayList<OffersModel>();
        modelList.add(new OffersModel("Get Upto 20% Off Clothing", 300));
        modelList.add(new OffersModel("Free Smart Phone", 200));
        modelList.add(new OffersModel("Pay $100 get big HD TV", 600));
        modelList.add(new OffersModel("Get Upto 40% Off All", 500));
        modelList.add(new OffersModel("Buy One Get One Free", 100));
        modelList.add(new OffersModel("Pay $200 get Laptop", 1600));
        modelList.add(new OffersModel("Get Upto 50% Off Electronics", 300));
        modelList.add(new OffersModel("Free Movie Ticket", 400));
        modelList.add(new OffersModel("Pay $100 Travel Europe", 700));
        modelList.add(new OffersModel("Get Upto 27% Off Appliance", 600));
        modelList.add(new OffersModel("Get Upto 44% Off Jewellery", 700));
        modelList.add(new OffersModel("Free Coupons", 500));
        modelList.add(new OffersModel("Pay $100 get Tablet", 600));
        return modelList;
    }


    @Override
    public void onTemplateSelected(String templateId, String templateName) {

        if(!templateId.isEmpty())
        {
            offers.setText(templateId);
        }else {

        }

    }
}
