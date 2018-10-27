package carbit3333333.gmail.com.moneytracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    private List<Record> mData = new ArrayList<>();
    private RecyclerView reciklerView;
    private ItemlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_lists);
        reciklerView = findViewById(R.id.list);
        createData();
        adapter = new ItemlistAdapter();
        reciklerView.setLayoutManager(new LinearLayoutManager(this));
        reciklerView.setAdapter(adapter);
       // adapter.notifyDataSetChanged();

    }

    private void createData() {
        mData.add(new Record("Milk", 23));
        mData.add(new Record("Bread", 1));
        mData.add(new Record("Life", 14));
        mData.add(new Record("Snow", 100));
        mData.add(new Record("Book", 11));
        mData.add(new Record("macBook", 2000));
        mData.add(new Record("Lego one hondred socol", 400));
        mData.add(new Record("noutbook", 2000));
        mData.add(new Record("Шоколадка", 4));
        mData.add(new Record("Шкаф", 1445));
    }

    private class ItemlistAdapter extends RecyclerView.Adapter<RecordViewHolder>{

        @NonNull
        @Override
        public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecordViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_record, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
            Record record = mData.get(position);
            holder.eplayData(record);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        public RecordViewHolder(View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.title);
            price =itemView.findViewById(R.id.price);
        }
        public void eplayData(Record record){
            title.setText(record.getTitle());
            price.setText(record.getPrice());
        }
    }

}
