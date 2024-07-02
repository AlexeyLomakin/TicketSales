package com.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.domain.TicketsDomainEntity
import com.presentation.R
import com.presentation.databinding.AllTicketsItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class AllTicketsAdapter:  ListAdapter<TicketsDomainEntity, AllTicketsAdapter.AllTicketsViewHolder>(diffUtil)  {


    class AllTicketsViewHolder(private val binding: AllTicketsItemBinding) : ViewHolder(binding.root){
        fun bind(offers: TicketsDomainEntity) {

            if (offers.badge.isNotEmpty()) {
                binding.badge.visibility = View.VISIBLE
                binding.badge.text = offers.badge
            } else {
                binding.badge.visibility = View.GONE
            }

             "${offers.price} ₽".also { binding.priceTv.text = it }

            if (!offers.has_transfer) {
                binding.transferTv.visibility = View.VISIBLE
                binding.transferTv.text = R.string.without_transfer.toString()
            } else {
                binding.transferTv.visibility = View.GONE
            }

            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val departureTime = dateFormat.parse(offers.departure.date) ?: Date()
            val arrivalTime = dateFormat.parse(offers.arrival.date) ?: Date()

            val timeDifference = arrivalTime.time - departureTime.time
            val hours = TimeUnit.MILLISECONDS.toHours(timeDifference)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60

            "${hours}ч ${minutes}м в пути".also {binding.flightTimeTv.text =  it  }

            "${offers.departure.date} - ${offers.arrival.date}".also { binding.departureTimeTv.text = it }
            binding.departureAirportTv.text = offers.departure.airport
            binding.arrivalAirportTv.text = offers.arrival.airport
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTicketsViewHolder {
        val binding = AllTicketsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllTicketsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllTicketsViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TicketsDomainEntity>() {
            override fun areItemsTheSame(oldItem: TicketsDomainEntity, newItem: TicketsDomainEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TicketsDomainEntity, newItem: TicketsDomainEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}