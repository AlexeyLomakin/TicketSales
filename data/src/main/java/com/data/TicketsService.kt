package com.data

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET


interface TicketsService {

    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getAllOffers(): Response<OffersResponse>

    @GET
    suspend fun getAllTickets(): Flow<List<Tickets>>

    @GET
    suspend fun getAllTicketOffers(): Flow<List<TicketOffers>>
}