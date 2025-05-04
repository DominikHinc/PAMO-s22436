package pl.pjatk.myapplication.ui.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.pjatk.myapplication.R;
import pl.pjatk.myapplication.adapter.ShoppingListAdapter;
import pl.pjatk.myapplication.model.ShoppingItem;

public class ShoppingFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shopping, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.shoppingListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ShoppingItem> shoppingItems = new ArrayList<>();
        shoppingItems.add(new ShoppingItem("Makaron pełnoziarnisty"));
        shoppingItems.add(new ShoppingItem("Pierś z kurczaka"));
        shoppingItems.add(new ShoppingItem("Pesto"));
        shoppingItems.add(new ShoppingItem("Parmezan"));
        shoppingItems.add(new ShoppingItem("Jajka"));
        shoppingItems.add(new ShoppingItem("Papryka"));
        shoppingItems.add(new ShoppingItem("Szpinak"));
        shoppingItems.add(new ShoppingItem("Ser feta"));

        ShoppingListAdapter adapter = new ShoppingListAdapter(shoppingItems);
        recyclerView.setAdapter(adapter);

        return root;
    }
} 