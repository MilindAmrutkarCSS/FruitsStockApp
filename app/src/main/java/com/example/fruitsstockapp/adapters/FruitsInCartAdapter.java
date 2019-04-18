package com.example.fruitsstockapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fruitsstockapp.R;
import com.example.fruitsstockapp.interfaces.IFruitsInCartListener;
import com.example.fruitsstockapp.model.Fruit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FruitsInCartAdapter extends RecyclerView.Adapter<FruitsInCartAdapter.FruitHolder> {

    private Context context;
    private List<Fruit> fruitList;
    private IFruitsInCartListener iFruitsInCartListener;

    public FruitsInCartAdapter(Context context, List<Fruit> fruitList, IFruitsInCartListener iFruitsInCartListener) {
        this.context = context;
        this.fruitList = fruitList;
        this.iFruitsInCartListener = iFruitsInCartListener;
    }

    @NonNull
    @Override
    public FruitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fruits_cart_list, parent, false);
        return new FruitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.tvFruitName.setText(fruit.getFruitName());
        holder.tvFruitCartCount.setText(String.valueOf(fruit.getInCartCount()));
        holder.ibDeleteFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFruitsInCartListener.onItemClick(fruit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    public void setFruitList(List<Fruit> fruits) {
        this.fruitList = fruits;
        notifyDataSetChanged();
    }

    public class FruitHolder extends RecyclerView.ViewHolder {

        private TextView tvFruitName;
        private TextView tvFruitCartCount;
        private ImageButton ibDeleteFromCart;

        public FruitHolder(@NonNull View itemView) {
            super(itemView);
            tvFruitName = itemView.findViewById(R.id.tvFruitName);
            tvFruitCartCount = itemView.findViewById(R.id.tvFruitCartCount);
            ibDeleteFromCart = itemView.findViewById(R.id.ibDeleteFromCart);
        }
    }
}
