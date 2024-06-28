package com.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.domain.TicketsOffersDomainEntity
import com.presentation.R

class ChoosingCountryAdapter : ListAdapter<TicketsOffersDomainEntity, ChoosingCountryAdapter.TicketsOffersViewHolder>(diffUtil)  {


    class TicketsOffersViewHolder(view: View) : ViewHolder(view){
        fun bind(offers: TicketsOffersDomainEntity) {
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsOffersViewHolder {
        val view = LayoutInflater.from(parent.context.applicationContext)
            .inflate(
            R.layout.choosing_country_fragment,
            parent,
            false
        )
        return TicketsOffersViewHolder(view)
    }


    override fun onBindViewHolder(holder: TicketsOffersViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TicketsOffersDomainEntity>() {
            override fun areItemsTheSame(oldItem: TicketsOffersDomainEntity, newItem: TicketsOffersDomainEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TicketsOffersDomainEntity, newItem: TicketsOffersDomainEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}