package com.presentation.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent.ACTION_UP
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.presentation.R
import com.presentation.databinding.FragmentOffersBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OffersFragment: Fragment(R.layout.fragment_offers){

    private val viewBinding by viewBinding(FragmentOffersBinding::bind)
    private val adapter = OffersAdapter()
    private val viewModel : OffersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewBinding.rvOffers.adapter = adapter
        viewBinding.rvOffers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val spacingItemDecoration = SpacingItemDecoration(25)
        viewBinding.rvOffers.addItemDecoration(spacingItemDecoration)
        viewBinding.rvOffers.addOnScrollListener(ScrollListener())
        viewModel.offersData.observe(viewLifecycleOwner){ offersData ->
                adapter.submitList(offersData)
        }

        viewBinding.arrival.apply {
            setOnTouchListener { v, event ->
                if (event.action == ACTION_UP) {
                    val departureText = viewBinding.departure.text.toString()
                    val bottomSheetFragment = BottomSheetFragment.newInstance(departureText)
                    bottomSheetFragment.show(
                        requireActivity().supportFragmentManager,
                        BottomSheetFragment.TAG
                    )
                    v.performClick()
                }
                false
            }
        }
    }

    inner class SpacingItemDecoration(private val horizontalSpaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.right = horizontalSpaceHeight
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