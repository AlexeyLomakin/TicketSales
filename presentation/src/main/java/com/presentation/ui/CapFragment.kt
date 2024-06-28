package com.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CapFragment : Fragment(R.layout.cap_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, OffersFragment())
                    .commit()
            }.isEnabled = true
        }
    }
}
