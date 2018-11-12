package carbit3333333.gmail.com.moneytracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ItemlistAdapter extends RecyclerView.Adapter<ItemlistAdapter.RecordViewHolder>{

    private List<Item> data = new ArrayList<>();

    public void setData(List<Item>data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemlistAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);

        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemlistAdapter.RecordViewHolder holder, int position) {
        Item item = data.get(position);
        holder.eplayData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(Item item) {
        data.add(item);
        notifyItemInserted(data.size());

    }


    static class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        public RecordViewHolder(View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.title);
            price =itemView.findViewById(R.id.price);
        }
        public void eplayData(Item item){
            title.setText(item.name);
            price.setText(item.price);
        }
    }
}
