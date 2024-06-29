package com.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.domain.GetAllTicketsOffersUseCase
import com.domain.TicketsOffersDomainEntity
import javax.inject.Inject

class ChoosingCountryViewModel @Inject constructor(
    getAllTicketsOffersUseCase: GetAllTicketsOffersUseCase
) : ViewModel() {
    val ticketsOffersData : LiveData<List<TicketsOffersDomainEntity>> = getAllTicketsOffersUseCase().asLiveData()
}