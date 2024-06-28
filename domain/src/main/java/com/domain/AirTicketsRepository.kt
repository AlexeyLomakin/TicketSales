package com.domain

import kotlinx.coroutines.flow.Flow


interface AirTicketsRepository {

    fun getAllTickets(): Flow<List<TicketsDomainEntity>>

    fun getAllOffers(): Flow<List<OffersDomainEntity>>

    fun getAllTicketsOffers(): Flow<List<TicketsOffersDomainEntity>>
}