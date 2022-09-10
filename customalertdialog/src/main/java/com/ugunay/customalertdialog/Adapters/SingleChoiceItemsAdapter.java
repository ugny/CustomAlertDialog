package com.ugunay.customalertdialog.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ugunay.customalertdialog.CustomAlertDialog;
import com.ugunay.customalertdialog.R;
import com.ugunay.customalertdialog.databinding.SingleChoiceItemBinding;

public class SingleChoiceItemsAdapter extends RecyclerView.Adapter<SingleChoiceItemsAdapter.SingleChoiceItemsViewHolder> {

    private final CharSequence[] items;
    private int checkedItemIndex;
    private final CustomAlertDialog.OnItemClickListener onItemClickListener;
    private final CustomAlertDialog dialog;
    private final int messageTextColor;
    private final int fontResId;
    private final boolean isLocalTypeface;

    public SingleChoiceItemsAdapter(CharSequence[] items, int checkedItemIndex,
                                    CustomAlertDialog.OnItemClickListener onItemClickListener,
                                    CustomAlertDialog dialog, int messageTextColor, int fontResId) {
        this.items = items;
        this.onItemClickListener = onItemClickListener;
        this.dialog = dialog;
        this.messageTextColor = messageTextColor;
        this.fontResId = fontResId;
        isLocalTypeface = fontResId == R.font.imprima;

        if (checkedItemIndex > -1 && checkedItemIndex < items.length) {
            this.checkedItemIndex = checkedItemIndex;
        } else {
            this.checkedItemIndex = 0;
        }
    }

    @NonNull
    @Override
    public SingleChoiceItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SingleChoiceItemBinding binding = SingleChoiceItemBinding.inflate(inflater, parent, false);
        return new SingleChoiceItemsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleChoiceItemsViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    private RadioButton lastCheckedItem;

    class SingleChoiceItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final SingleChoiceItemBinding binding;
        private final Context context;

        public SingleChoiceItemsViewHolder(@NonNull SingleChoiceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            context = binding.getRoot().getContext();
            binding.rdoBtnItem.setOnClickListener(this);
        }

        public void bindData(int position) {
            binding.rdoBtnItem.setText(items[position]);
            binding.rdoBtnItem.setTextColor(messageTextColor);
            if (position == checkedItemIndex) {
                binding.rdoBtnItem.setChecked(true);
                lastCheckedItem = binding.rdoBtnItem;
            } else {
                binding.rdoBtnItem.setChecked(false);
            }

            if (!isLocalTypeface) {
                binding.rdoBtnItem.setTypeface(ResourcesCompat.getFont(context, fontResId));
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAbsoluteAdapterPosition();
            // Aynı elemena tıklanmışssa bir şey yapmaya gerek yoktur.
            if (checkedItemIndex == position) return;

            checkedItemIndex = position;
            lastCheckedItem.setChecked(false);
            lastCheckedItem = (RadioButton) v;
            onItemClickListener.onClick(dialog, position);
        }
    }

}
