package com.example.waslatask.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.waslatask.R
import com.example.waslatask.databinding.ResultRowBinding
import javax.inject.Inject

class QueriesAdapter @Inject constructor() : RecyclerView.Adapter<QueriesAdapter.QueryVH>() {

    private var list: List<String> = ArrayList()


    fun setList(list: List<String>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryVH {
        val rowBinding: ResultRowBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.result_row,
                parent,
                false
            )

        return QueryVH(rowBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: QueryVH, position: Int) {

        holder.rowBinding.text = list[position]
    }


    class QueryVH(var rowBinding: ResultRowBinding) : RecyclerView.ViewHolder(rowBinding.root) {

    }
}