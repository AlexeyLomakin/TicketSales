package com.data

import com.domain.AirTicketsRepository
import com.domain.OffersDomainEntity
import com.domain.TicketsDomainEntity
import com.domain.TicketsOffersDomainEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AirTicketsRepositoryImpl @Inject constructor(
    private val ticketsService: TicketsService,
    private val offersResponseMapper: Mapper<OffersResponse.Offer, OffersDomainEntity>,
    private val ticketsMapper: Mapper<Tickets, TicketsDomainEntity>,
    private val ticketsOffersResponseMapper: Mapper<TicketsOffers, TicketsOffersDomainEntity>
): AirTicketsRepository {
    override fun getAllTickets(): Flow<List<TicketsDomainEntity>> = flow {
        val response: Response<List<Tickets>> = ticketsService.getAllTickets()
        if (response.isSuccessful) {
            val tickets = response.body() ?: emptyList()
            val ticketsDomainEntities = tickets.map {
                ticketsMapper.map(it)
            }
            emit(ticketsDomainEntities)
        }
    }
    override fun getAllTicketsOffers(): Flow<List<TicketsOffersDomainEntity>> = flow {
        val response: Response<List<TicketsOffers>> = ticketsService.getAllTicketOffers()
        if (response.isSuccessful) {
            val tickets = response.body() ?: emptyList()
            val ticketsOffersEntities = tickets.map {
                ticketsOffersResponseMapper.map(it)
            }
            emit(ticketsOffersEntities)
        }
    }

    override fun getAllOffers(): Flow<List<OffersDomainEntity>> = flow {
        val response: Response<OffersResponse> = ticketsService.getAllOffers()
        if (response.isSuccessful) {
            val offers = response.body()?.offers ?: emptyList()
            val offersDomainEntities = offers.map { offersResponseMapper.map(it) }
            emit(offersDomainEntities)
        } else {
            throw Exception("Failed to fetch offers: ${response.errorBody()?.string()}")
        }
    }
}