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
import com.ugunay.customalertdialog.databinding.MultiChoiceItemBinding;

public class MultiChoiceItemsAdapter extends RecyclerView.Adapter<MultiChoiceItemsAdapter.MultiChoiceItemsViewHolder> {

    private final CharSequence[] items;
    private final boolean[] checkedItems;
    private final CustomAlertDialog.OnMultiChoiceClickListener onMultiChoiceClickListener;
    private final CustomAlertDialog dialog;
    private final int messageTextColor;
    private final AppCompatActivity activity;
    private final int fontResId;
    private final boolean isLocalTypeface;

    public MultiChoiceItemsAdapter(CharSequence[] items, boolean[] checkedItems,
                                   CustomAlertDialog.OnMultiChoiceClickListener onMultiChoiceClickListener,
                                   CustomAlertDialog dialog, int messageTextColor,
                                   AppCompatActivity activity, int fontResId) {
        this.items = items;
        this.checkedItems = checkedItems;
        this.onMultiChoiceClickListener = onMultiChoiceClickListener;
        this.dialog = dialog;
        this.messageTextColor = messageTextColor;
        this.activity = activity;
        this.fontResId = fontResId;
        isLocalTypeface = fontResId == R.font.imprima;
    }

    @NonNull
    @Override
    public MultiChoiceItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MultiChoiceItemBinding binding = MultiChoiceItemBinding.inflate(inflater, parent, false);
        return new MultiChoiceItemsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiChoiceItemsAdapter.MultiChoiceItemsViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class MultiChoiceItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final MultiChoiceItemBinding binding;

        public MultiChoiceItemsViewHolder(@NonNull MultiChoiceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.chkItem.setOnClickListener(this);
        }

        public void bindData(int position) {
            binding.chkItem.setChecked(checkedItems[position]);
            binding.chkItem.setText(items[position]);
            binding.chkItem.setTextColor(messageTextColor);

            if (!isLocalTypeface) {
                binding.chkItem.setTypeface(ResourcesCompat.getFont(activity, fontResId));
            }
        }

        @Override
        public void onClick(View view) {
            int position = getAbsoluteAdapterPosition();
            checkedItems[position] = !checkedItems[position];
            onMultiChoiceClickListener.onClick(dialog, position, checkedItems[position]);
        }
    }

}
