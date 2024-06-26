package com.presentation.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.presentation.R
import com.presentation.databinding.BottomSheetSearchBinding


class BottomSheetFragment : BottomSheetDialogFragment(R.layout.bottom_sheet_search) {

    private val viewBinding by viewBinding(BottomSheetSearchBinding::bind)
    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }
        dialog.setCancelable(false)
        return dialog
    }

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
        val behavior = BottomSheetBehavior.from(bottomSheet!!)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val departure = arguments?.getString(ARG_DEPARTURE) ?: ""
        viewBinding.departure.text = departure
    }

    companion object {
        const val TAG = "BottomSheetFragment"
        private const val ARG_DEPARTURE = "departure"

        fun newInstance(departure: String): BottomSheetFragment {
            val args = Bundle().apply {
                putString(ARG_DEPARTURE, departure)
            }
            val fragment = BottomSheetFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

