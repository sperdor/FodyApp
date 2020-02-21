package com.fody.app;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String[] dataset ;
    private List<entityBreakfast> breakfast = new ArrayList<>();
    private FirebaseAuth mAuth;
    Date date = new Date();
    private entityBreakfast test = new entityBreakfast();


    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView textQty;


        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.textView);
            textQty = (TextView) v.findViewById(R.id.txtViewQty);
        }

        public TextView getTextView() {
            return textView;
        }
        public TextView getTextViewQty() {
            return textQty;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     *
     */
    public BreakfastAdapter(List<entityBreakfast> Breakfast) {

        if(Breakfast.isEmpty()){

        }else {
            this.breakfast=Breakfast;
        }





    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        {
            viewHolder.getTextView().setText(breakfast.get(position).getNameFood());
            viewHolder.getTextViewQty().setText(breakfast.get(position).getQuantity().toString());


        }

    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(breakfast.isEmpty()){
            return 0;
        }else {
            return breakfast.size();
        }
    }


}
