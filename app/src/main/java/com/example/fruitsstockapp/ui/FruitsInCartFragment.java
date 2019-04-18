package com.example.fruitsstockapp.ui;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fruitsstockapp.R;
import com.example.fruitsstockapp.adapters.FruitsInCartAdapter;
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
public class FruitsInCartFragment extends Fragment implements IFruitsInCartListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Context context;
    private FruitViewModel fruitViewModel;
    private List<Fruit> fruitList = new ArrayList<>();
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fruits_in_cart, container, false);

        unbinder = ButterKnife.bind(this, view);

        fruitViewModel = ViewModelProviders.of(this).get(FruitViewModel.class);

        FruitsInCartAdapter fruitsInCartAdapter = new FruitsInCartAdapter(context, fruitList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(fruitsInCartAdapter);

        fruitViewModel.getAllFruitsInCart().observe(this, new Observer<List<Fruit>>() {
            @Override
            public void onChanged(List<Fruit> fruits) {
                fruitList = fruits;
                fruitsInCartAdapter.setFruitList(fruitList);
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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void updateFruit(Fruit fruit) {
        int cartCount = fruit.getInCartCount();
        if ((cartCount - 1) == 0) {
            fruit.setInCart(false);
        }
        fruit.setInCartCount(fruit.getInCartCount() - 1);
        fruit.setInStockCount(fruit.getInStockCount() + 1);
        fruitViewModel.update(fruit);
    }
}
