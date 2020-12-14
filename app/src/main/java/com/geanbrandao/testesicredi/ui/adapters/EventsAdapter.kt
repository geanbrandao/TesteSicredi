package com.geanbrandao.testesicredi.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geanbrandao.testesicredi.R
import com.geanbrandao.testesicredi.convertToDate
import com.geanbrandao.testesicredi.convertoToDateString
import com.geanbrandao.testesicredi.databinding.ItemEventBinding
import com.geanbrandao.testesicredi.loadImage
import com.geanbrandao.testesicredi.model.Event

class EventsAdapter(
    private val context: Context,
    private val onClick: (event: Event) -> Unit,
): RecyclerView.Adapter<EventsAdapter.MyViewHolder>() {
    private val data: ArrayList<Event> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        holder.bindView(item)
        holder.binding.item.setOnClickListener {
            onClick.invoke(item)
        }
    }

    override fun getItemCount() = data.count()

    fun addAll(data: ArrayList<Event>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        val binding: ItemEventBinding = ItemEventBinding.bind(itemView)

        fun bindView(item: Event) {
            binding.textName.text = item.title
            binding.textDescription.text = item.description
            binding.textDate.text = item.date.convertoToDateString()
            binding.imageEvent.loadImage(item.image)
        }
    }
}