package com.innobot.myrecyclerwithradiobutton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by innobot-linux-7 on 7/5/18,07,MyApplication.
 */
public class OffersRecyclerViewAdapter extends
        RecyclerView.Adapter<OffersRecyclerViewAdapter.ViewHolder>   {

    private List<OffersModel> offersList;
    private Context context;
    private RadioButton lastChecked = null;
    private int lastSelectedPosition = -1;
    TemplateSelectionListener Listenter ;


    public OffersRecyclerViewAdapter(List<OffersModel> offersListIn
            , Context ctx,TemplateSelectionListener Listenter) {
        offersList = offersListIn;
        context = ctx;
        this.Listenter =  Listenter;
    }

    @Override
    public OffersRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_officer, parent, false);

        OffersRecyclerViewAdapter.ViewHolder viewHolder =
                new OffersRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OffersRecyclerViewAdapter.ViewHolder holder,
                                 final int position) {

        final int pos = position;
        final OffersModel offersModel = offersList.get(position);
        String offers = offersModel.getOffer();
        holder.offerName.setText(offers);
        holder.offerAmount.setText("" + offersModel.getSavings());

        //since only one radio button is allowed to be selected,
        // this condition un-checks previous selections
        holder.selectionState.setChecked(lastSelectedPosition == position);


        holder.selectionState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = pos;
                RadioButton cb = (RadioButton) v;
//                int clickedPos = ((Integer) cb.getTag()).intValue();
                if (cb.isChecked()) {
                    if (lastChecked != null) {
                        lastChecked.setChecked(false);
                    }
                    lastChecked = cb;
                } else {
                    lastChecked = null;
                }
                notifyDataSetChanged();
                Toast.makeText(OffersRecyclerViewAdapter.this.context,"selected offer is " + offersModel.getOffer(),
                        Toast.LENGTH_LONG).show();

                Listenter.onTemplateSelected("" + offersModel.getSavings(),"");


            }
        });

    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    public List<OffersModel> getAdapterPosition() {
        return offersList;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView offerName;
        public TextView offerAmount;
        public RadioButton selectionState;

        public ViewHolder(View view) {
            super(view);
            offerName = (TextView) view.findViewById(R.id.offer_name);
            offerAmount = (TextView) view.findViewById(R.id.offer_amount);
            selectionState = (RadioButton) view.findViewById(R.id.offer_select);


        }
    }


}
