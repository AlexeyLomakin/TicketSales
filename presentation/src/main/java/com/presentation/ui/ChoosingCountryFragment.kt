package com.presentation.ui

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.presentation.R
import com.presentation.databinding.ChoosingCountryFragmentBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ChoosingCountryFragment: Fragment(R.layout.choosing_country_fragment) {

    private val viewBinding by viewBinding(ChoosingCountryFragmentBinding::bind)
    private val adapter = ChoosingCountryAdapter()
    private val viewModel : ChoosingCountryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("d MMMM", Locale("ru"))
        val formattedDate = dateFormat.format(calendar.time)
        viewBinding.departureDateBtn.text = formattedDate

        viewBinding.departureDateBtn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val selectedDate = dateFormat.format(calendar.time)
                viewBinding.departureDateBtn.text = selectedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }
}