package com.example.fruitsstockapp.ui;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fruitsstockapp.R;
import com.example.fruitsstockapp.adapters.FruitsInStockAdapter;
import com.example.fruitsstockapp.interfaces.IFruitsInCartListener;
import com.example.fruitsstockapp.model.Fruit;
import com.example.fruitsstockapp.viewmodel.FruitViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FruitsInStockFragment extends Fragment implements IFruitsInCartListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Context context;
    private FruitViewModel fruitViewModel;
    private List<Fruit> fruitList = new ArrayList<>();
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fruits_in_stock, container, false);
        unbinder = ButterKnife.bind(this, view);

        fruitViewModel = ViewModelProviders.of(this).get(FruitViewModel.class);

        FruitsInStockAdapter fruitsInStockAdapter = new FruitsInStockAdapter(context, fruitList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(fruitsInStockAdapter);

        fruitViewModel.getAllFruitsInStock().observe(this, new Observer<List<Fruit>>() {
            @Override
            public void onChanged(List<Fruit> fruits) {
                fruitList = fruits;
                fruitsInStockAdapter.setFruitList(fruitList);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onItemClick(Fruit fruit) {
        int stockCount = fruit.getInStockCount();
        if (stockCount != 0) {
            fruit.setInStockCount(stockCount - 1);
            fruit.setInCartCount(fruit.getInCartCount() + 1);
            fruit.setInCart(true);
        }
        fruitViewModel.update(fruit);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
