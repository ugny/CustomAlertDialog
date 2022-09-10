package com.ugunay.customalertdialog.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ugunay.customalertdialog.CustomAlertDialog;
import com.ugunay.customalertdialog.R;
import com.ugunay.customalertdialog.databinding.ListItemBinding;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private final CharSequence[] items;
    private final CustomAlertDialog.OnItemClickListener onItemClickListener;
    private final CustomAlertDialog dialog;
    private final int messageTextColor;
    private final AppCompatActivity activity;
    private final int fontResId;
    private final boolean isLocalTypeface;

    public ItemsAdapter(CharSequence[] items, CustomAlertDialog.OnItemClickListener onItemClickListener,
                        CustomAlertDialog dialog, int messageTextColor,
                        AppCompatActivity activity, int fontResId) {
        this.items = items;
        this.onItemClickListener = onItemClickListener;
        this.dialog = dialog;
        this.messageTextColor = messageTextColor;
        this.activity = activity;
        this.fontResId = fontResId;
        isLocalTypeface = fontResId == R.font.imprima;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding binding = ListItemBinding.inflate(inflater, parent, false);
        return new ItemsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemsViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ListItemBinding binding;

        public ItemsViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        public void bindData(int position) {
            binding.txtItem.setText(items[position]);
            binding.txtItem.setTextColor(messageTextColor);

            if (!isLocalTypeface) {
                binding.txtItem.setTypeface(ResourcesCompat.getFont(activity, fontResId));
            }
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(dialog, getAbsoluteAdapterPosition());
        }
    }

}
