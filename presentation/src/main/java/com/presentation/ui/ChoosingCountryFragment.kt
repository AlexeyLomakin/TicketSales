package com.presentation.ui

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.presentation.R
import com.presentation.databinding.ChoosingCountryFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale


@AndroidEntryPoint
class ChoosingCountryFragment: Fragment(R.layout.choosing_country_fragment) {

    private val viewBinding by viewBinding(ChoosingCountryFragmentBinding::bind)
    private val adapter = ChoosingCountryAdapter()
    private val viewModel : ChoosingCountryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.ticketRecommendations.layoutManager = LinearLayoutManager(requireContext())

        viewBinding.ticketRecommendations.adapter  = adapter
        viewModel.ticketsOffersData.observe(viewLifecycleOwner){ ticketsOffersData ->
            adapter.submitList(ticketsOffersData)
        }
        viewBinding.backBtn.setOnClickListener{
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

        viewBinding.btnSwap.setOnClickListener {
            val arrivalText: String = viewBinding.arrival.text.toString()
            viewBinding.arrival.text = Editable
                .Factory
                .getInstance()
                .newEditable(viewBinding.departure.text)
            viewBinding.departure.text = arrivalText
        }


        val calendar = java.util.Calendar.getInstance()
        val dateFormat = SimpleDateFormat("d MMM", Locale("ru"))
        val dayFormat = SimpleDateFormat("EE", Locale("ru"))

        val formattedDate = dateFormat.format(calendar.time)
        val formattedDay = dayFormat.format(calendar.time)
        val fullText = "$formattedDate, $formattedDay"
        val spannable = SpannableString(fullText)
        val dateEndIndex = formattedDate.length

        spannable.setSpan(
            ForegroundColorSpan(Color.WHITE),
            0,
            dateEndIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(Color.GRAY),
            dateEndIndex,
            fullText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        viewBinding.departureDateBtn.text = spannable

        viewBinding.departureDateBtn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    calendar.set(java.util.Calendar.YEAR, year)
                    calendar.set(java.util.Calendar.MONTH, monthOfYear)
                    calendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth)

                    val selectedDate = dateFormat.format(calendar.time)
                    val selectedDay = dayFormat.format(calendar.time)
                    val selectedFullText = "$selectedDate, $selectedDay"

                    val selectedSpannable = SpannableString(selectedFullText)
                    val selectedDateEndIndex = selectedDate.length

                    selectedSpannable.setSpan(
                        ForegroundColorSpan(Color.WHITE),
                        0,
                        selectedDateEndIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    selectedSpannable.setSpan(
                        ForegroundColorSpan(Color.GRAY),
                        selectedDateEndIndex,
                        selectedFullText.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    viewBinding.departureDateBtn.text = selectedSpannable
                },
                calendar.get(java.util.Calendar.YEAR),
                calendar.get(java.util.Calendar.MONTH),
                calendar.get(java.util.Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }
}