package com.example.heroapi.ui.adapter

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.heroapi.R
import com.example.heroapi.databinding.ItemHeroBinding
import com.example.heroapi.model.*
import com.example.heroapi.ui.util.RecyclerViewLoadMore
import com.example.heroapi.ui.view.HeroDetailsFragment
import com.example.heroapi.ui.view.RecyclerListFragment
import com.squareup.picasso.Picasso

class HeroAdapter() : RecyclerView.Adapter<HeroAdapter.MyViewHolder>() {
    var items = ArrayList<HeroModel>()

    fun setUpdatedData(items: ArrayList<HeroModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    //Funcion para agregar un nuevo renglon a la lista y agregar un objeto vacio, para despues llenar ese objeto
    fun addLoadingView() {
        //add loading item
        Handler().post {
            val hero = HeroModel(
                "", "", "", ImageModel(""), PowerStatsModel("", "", "", "", "", ""),
                BiographyModel("","", arrayListOf(),"","","",""), AppearanceModel("","", arrayListOf(),
                    arrayListOf(),"",""),
                WorkModel("", ""), ConnectionsModel("", ""),
            )

            items.add(hero)
            notifyItemInserted(items.size - 1)
        }
    }
    //Funcion para remover ese objeto
    fun removeLoadingView() {
        //Remove loading item
        if (items.size != 0) {
            items.removeAt(items.size - 1)
            notifyItemRemoved(items.size)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return if (viewType == RecyclerViewLoadMore.Constant.VIEW_TYPE_ITEM) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
            MyViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.progress_loading, parent, false)
            MyViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (holder.itemViewType == RecyclerViewLoadMore.Constant.VIEW_TYPE_ITEM) {
            holder.bind(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) {
            RecyclerViewLoadMore.Constant.VIEW_TYPE_LOADING
        } else {
            RecyclerViewLoadMore.Constant.VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemHeroBinding.bind(view)
        val activity = view.context as AppCompatActivity

        private fun heroDetails(data: HeroModel) {
            val newFragment = HeroDetailsFragment.newInstance()
            val bundle = Bundle()
            bundle.putParcelable("hero", data)
            newFragment.arguments = bundle
            val fm = this.activity.supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            //ft.hide(RecyclerListFragment.newInstance())
            ft.replace(R.id.container, newFragment)
            //R.id.containerListFragment.
            ft.commit()
        }

        fun bind(data: HeroModel) {
            if (data.response == "success") {
                binding.tvName.text = data.name

                val url = data.image.url
                Picasso.get()
                    .load(url)
                    .into(binding.ivHero)
            }

            binding.tvName.setOnClickListener {
                heroDetails(data)
            }
            binding.ivHero.setOnClickListener {
                heroDetails(data)
            }

            this.itemView.setOnClickListener {
                heroDetails(data  )
            }
        }
    }


}