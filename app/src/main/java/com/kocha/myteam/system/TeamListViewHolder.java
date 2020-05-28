package com.kocha.myteam.system;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kocha.myteam.databinding.ActivityTeamListRecyclerViewItemBinding;

public class TeamListViewHolder extends RecyclerView.ViewHolder {

    public ActivityTeamListRecyclerViewItemBinding binding;

    // Для работы Холдера необходим Биндинг
    public TeamListViewHolder(ActivityTeamListRecyclerViewItemBinding binding) {
        // Отдаем базовому классу базовый View из Биндинга
        super(binding.getRoot());
        this.binding = binding;
        // Назначаем базовому View OnClickListener
        itemView.setOnClickListener(new HolderOnClickListener());
    }

    class HolderOnClickListener implements View.OnClickListener {

        @Override
        // Описываем, что происходит при клике на пункте списка RecyclerView
        public void onClick(View v) {
            String text = "Позиция: " + getAdapterPosition();
            Toast.makeText(v.getContext(), text, Toast.LENGTH_SHORT).show();
        }
    }
}
