package carbit3333333.gmail.com.moneytracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsFragment extends Fragment {

    private static final String TAG = "ItemsFragment :";
    private String type;
    private static final String TYPE_KEY = "type";
    public static final int ADD_ITEM_REQEST_CODE = 123;


    public static ItemsFragment createItemFragment(String type) {
        ItemsFragment fragment = new ItemsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ItemsFragment.TYPE_KEY, type);
        fragment.setArguments(bundle);
        return (ItemsFragment) fragment;
    }


    private List<Item> mData = new ArrayList<>();
    private RecyclerView reciklerView;
    private ItemlistAdapter adapter;
    private Api api;
    SwipeRefreshLayout refreshLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemlistAdapter();
        Bundle bundle = getArguments();
        type = bundle.getString(TYPE_KEY, Item.TYPE_EXPENSES);

        if (type.equals(Item.TYPE_UNKNOWN)) {
            throw new IllegalArgumentException("Unknown type");
        }
        api = ((App) getActivity().getApplication()).getApi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        view.findViewById(R.id.list);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reciklerView = view.findViewById(R.id.list);
        reciklerView.setLayoutManager(new LinearLayoutManager(getContext()));

        reciklerView.setAdapter(adapter);
        refreshLayout = view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItem();

            }
        });
        loadItem();
    }

    private void loadItem() {
        Call<List<Item>> call = api.getItems(type);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                adapter.setData(response.body());
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                refreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_ITEM_REQEST_CODE && resultCode == Activity.RESULT_OK) {
            Item item = (Item) data.getParcelableExtra("item");
            if (item.type.equals(type)) {
                adapter.addItem(item);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
