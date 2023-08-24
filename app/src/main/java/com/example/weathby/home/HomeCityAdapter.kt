package com.example.weathby.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weathby.databinding.ItemCityCardBinding


class HomeCityAdapter(private val onClickListener: OnClickListener): ListAdapter<CityCard, HomeCityAdapter.CityViewHolder>(DiffCallback) {

    class OnClickListener(val clickListener: (city: CityCard, extra: FragmentNavigator.Extras) -> Unit) {
        fun onClick(city: CityCard, extra: FragmentNavigator.Extras) = clickListener(city, extra)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(ItemCityCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.setupView(currentList[position])
    }

    inner class CityViewHolder(private val binding: ItemCityCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun setupView(city: CityCard) = binding.apply {
            val extras = FragmentNavigatorExtras(
                cityCard to "detailBackground",
                cityName to "detailCity",
                weekName to "detailWeek",
                cityTem to "detailTerm",
                cityWindyIcon to "detailWindyIcon",
                cityWindy to "detailWindy",
                cityRainyIcon to "detailRainyIcon",
                cityRainy to "detailRainy",
                cityWetIcon to "detailWetIcon",
                cityWet to "detailWet"
            )
            cityName.text = city.cityName
            weekName.text = city.day
            cityTem.text = city.temp
            cityWindy.text = city.wind
            cityWet.text = city.wet
            cityRainy.text = city.rain
            root.setOnClickListener {
                onClickListener.onClick(city, extras)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CityCard>() {
        override fun areItemsTheSame(oldItem: CityCard, newItem: CityCard): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: CityCard, newItem: CityCard): Boolean {
            return oldItem.id == newItem.id
        }
    }
}