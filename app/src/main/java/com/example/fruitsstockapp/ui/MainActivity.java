package com.example.fruitsstockapp.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fruitsstockapp.R;
import com.example.fruitsstockapp.adapters.ViewPagerAdapter;
import com.example.fruitsstockapp.model.Fruit;
import com.example.fruitsstockapp.viewmodel.FruitViewModel;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapter viewPagerAdapter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    FruitViewModel fruitViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fruitViewModel = ViewModelProviders.of(this).get(FruitViewModel.class);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fruit:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.custom_add_fruit);
                dialog.setTitle("Add Fruit");

                EditText etFruitName = dialog.findViewById(R.id.etFruitName);
                EditText etFruitCount = dialog.findViewById(R.id.etFruitCount);
                Button btnAddFruit = dialog.findViewById(R.id.btnAddToStock);

                btnAddFruit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!TextUtils.isEmpty(etFruitName.getText().toString())
                                && (!TextUtils.isEmpty(etFruitCount.getText().toString()))) {

                            String fruitName = etFruitName.getText().toString();
                            int fruitCount = Integer.valueOf(etFruitCount.getText().toString());

                            if (fruitCount <= 50) {
                                Fruit fruit = new Fruit();
                                fruit.setFruitName(fruitName);
                                fruit.setInStockCount(fruitCount);
                                fruitViewModel.insert(fruit);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(MainActivity.this, "Fruits quantity cannot be greater than 50", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Fruit Name & Count cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
        }
        return super.onOptionsItemSelected(item);

    }
}
