package com.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.domain.GetAllTicketsUseCase
import com.domain.TicketsDomainEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AllTicketsViewModel @Inject constructor(
    getAllTicketsUseCase: GetAllTicketsUseCase
): ViewModel() {
    val  ticketsData: LiveData<List<TicketsDomainEntity>> = getAllTicketsUseCase().asLiveData()
}