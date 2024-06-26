package com.domain

import kotlinx.coroutines.flow.Flow


interface AirTicketsRepository {

    suspend fun getAllTickets(): Flow<List<TicketsDomainEntity>>

    suspend fun getAllOffers(): Flow<List<OffersDomainEntity>>
}