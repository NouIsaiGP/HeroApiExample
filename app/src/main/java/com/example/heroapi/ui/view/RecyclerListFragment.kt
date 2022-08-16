package com.example.heroapi.ui.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heroapi.R
import com.example.heroapi.databinding.ActivityMainBinding
import com.example.heroapi.databinding.FragmentRecyclerListBinding
import com.example.heroapi.model.*
import com.example.heroapi.ui.adapter.HeroAdapter
import com.example.heroapi.ui.util.OnLoadMoreListener
import com.example.heroapi.ui.util.RecyclerViewLoadMore
import com.example.heroapi.ui.view.RecyclerListFragment.Companion.newInstance
import com.example.heroapi.ui.viewmodel.HeroViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecyclerListFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var recyclerAdapter: HeroAdapter
    private lateinit var binding: FragmentRecyclerListBinding
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var scrollListener: RecyclerViewLoadMore
    private val viewModel  by activityViewModels<HeroViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerListBinding.inflate(inflater)

        val view = binding.root

        initViewModel(view)
        initViewModel()
        setRVScrollListener()
        return view
    }

    private fun initViewModel(view: View) {
        layoutManager = LinearLayoutManager(activity)
        binding.rvHero.layoutManager = layoutManager
        val decortion = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.rvHero.addItemDecoration(decortion)

        recyclerAdapter = HeroAdapter()
        binding.rvHero.adapter = recyclerAdapter

        binding.searchHero.setOnQueryTextListener(this)

    }

    private fun setRVScrollListener() {
        layoutManager = LinearLayoutManager(activity)
        scrollListener = RecyclerViewLoadMore(layoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object :
            OnLoadMoreListener {
            override fun onLoadMore() {
                LoadMoreData()
            }
        })
        binding.rvHero.addOnScrollListener(scrollListener)
    }

    private fun LoadMoreData() {
        recyclerAdapter.addLoadingView()
        Handler().postDelayed({
            addFiveMore()
            recyclerAdapter.removeLoadingView()
            //recyclerAdapter.addData(loadMoreItemsCells)
            scrollListener.setLoaded()
            binding.rvHero.post {
                recyclerAdapter.notifyDataSetChanged()
            }
        }, 5000)

    }

    private fun initViewModel() {
       // viewModel = ViewModelProvider(this).get(HeroViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<ResponseApi> {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it.results)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

    override fun onDestroyView() {
        viewModel.getRecyclerListObserver().removeObserver { viewLifecycleOwner }
        super.onDestroyView()
    }

    private fun addFiveMore() {
        //viewModel = ViewModelProvider(this).get(HeroViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<ResponseApi> {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it.results)

            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.addFiveMore()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchByName(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return true
    }



    private fun showErrorDialog() {
        Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        val imm =
            activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.containerListFragment.windowToken, 0)
    }


    private fun searchByName(name: String) {
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<ResponseApi> {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it.results)

            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.findHeroByName(name)
        scrollListener.setOnLoadMoreListener(object :
            OnLoadMoreListener {
            override fun onLoadMore() {
                //LoadMoreData()
            }
        })
        binding.rvHero.addOnScrollListener(scrollListener)


    }


}