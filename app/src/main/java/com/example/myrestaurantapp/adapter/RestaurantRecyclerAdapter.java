package com.example.myrestaurantapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrestaurantapp.R;
import com.example.myrestaurantapp.restaurant.OperatingHours;
import com.example.myrestaurantapp.restaurant.ReviewModel;

import java.text.MessageFormat;
import java.util.List;

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.MyViewHolder> {

    private final Context context;
    private List<AdapterModel> list;

    public RestaurantRecyclerAdapter(Context context, List<AdapterModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
        AdapterModel adapterModel = list.get(position);

        if(adapterModel!=null && adapterModel.getRestaurantModel()!=null){
            setRestaurantReviews(holder, adapterModel.getRestaurantModel().getReviews());
            setRestaurantOperatingHours(holder, adapterModel.getRestaurantModel().getOperating_hours());
            holder.txtCuisine.setText(MessageFormat.format("Cuisine Name: {0}", adapterModel.getRestaurantModel().getCuisine_type()));
            holder.txtResName.setText(MessageFormat.format("Restaurant Name: {0}", adapterModel.getRestaurantModel().getName()));
            holder.restaurantLayout.setVisibility(View.VISIBLE);
        }else{
            holder.restaurantLayout.setVisibility(View.GONE);
        }
        if(adapterModel!=null && adapterModel.getMenuItems()!=null){
            holder.txtMenuName.setText(MessageFormat.format("Menu Name: {0}", adapterModel.getMenuItems().getName()));
            holder.txtMenuDes.setText(MessageFormat.format("Description: {0}", adapterModel.getMenuItems().getDescription()));
            holder.txtMenuPrice.setText(MessageFormat.format("Price: {0}", adapterModel.getMenuItems().getPrice()));
            holder.menuItemLayout.setVisibility(View.VISIBLE);
        }else{
            holder.menuItemLayout.setVisibility(View.GONE);
        }
    }

    private void setRestaurantOperatingHours(MyViewHolder holder, OperatingHours operatingHours) {
        String operatingHr =
                "Monday: " + operatingHours.getMonday() + "\n" +
                "Tuesday: " + operatingHours.getTuesday() + "\n" +
                "Wednesday: " + operatingHours.getWednesday() + "\n" +
                "Thursday: " + operatingHours.getThursday() + "\n" +
                "Friday: " + operatingHours.getFriday() + "\n" +
                "Saturday: " + operatingHours.getSaturday() + "\n" +
                "Sunday: " + operatingHours.getSunday();
        holder.txtOperatingHours.setText(operatingHr);
    }

    private void setRestaurantReviews(MyViewHolder holder, List<ReviewModel> reviews) {
        StringBuilder stringBuilder = new StringBuilder();
        for(ReviewModel reviewModel : reviews){
            stringBuilder.append("Name: ").append(reviewModel.getName()).append("\n").append("Date: ").append(reviewModel.getDate()).append("\n").append("Rating: ").append(reviewModel.getRating()).append("\n").append("Comment: ").append(reviewModel.getComments()).append("\n\n");
        }
        holder.txtResReview.setText(stringBuilder.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtResName;
        TextView txtCuisine;

        TextView txtOperatingHours;

        TextView txtResReview;

        TextView txtMenuName;
        TextView txtMenuDes;
        TextView txtMenuPrice;

        LinearLayoutCompat restaurantLayout;
        LinearLayoutCompat menuItemLayout;

        public MyViewHolder(View view) {
            super(view);
            restaurantLayout = view.findViewById(R.id.restaurantLayout);
            menuItemLayout = view.findViewById(R.id.menuItemLayout);

            txtResName = view.findViewById(R.id.txtResName);
            txtCuisine = view.findViewById(R.id.txtCuisine);

            txtOperatingHours = view.findViewById(R.id.txtOperatingHours);

            txtResReview = view.findViewById(R.id.txtResReview);

            txtMenuName = view.findViewById(R.id.txtMenuName);
            txtMenuDes = view.findViewById(R.id.txtMenuDes);
            txtMenuPrice = view.findViewById(R.id.txtMenuPrice);
        }
    }
}
