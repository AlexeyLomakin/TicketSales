package com.presentation.ui

import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.presentation.R
import com.presentation.databinding.FragmentOffersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OffersFragment: Fragment(R.layout.fragment_offers){

    private val viewBinding by viewBinding(FragmentOffersBinding::bind)
    private val adapter = OffersAdapter()
    private val viewModel : OffersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewBinding.rvOffers.adapter = adapter
        viewBinding.rvOffers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewBinding.rvOffers.addOnScrollListener(ScrollListener())
        viewModel.offersData.observe(viewLifecycleOwner){ offersData ->
                adapter.submitList(offersData)
        }

        viewBinding.arrival.setOnTouchListener { v, event ->
            if (event.action == ACTION_UP) {
                val departureText = viewBinding.departure.text.toString()
                val bottomSheetFragment = BottomSheetFragment.newInstance(departureText)
                bottomSheetFragment.show(
                    requireActivity().supportFragmentManager,
                    BottomSheetFragment.TAG
                )
                v.performClick()
            }
            true
        }
    }

    inner class ScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollHorizontally(1)) {
                viewModel.offersData
            }
        }
    }
}