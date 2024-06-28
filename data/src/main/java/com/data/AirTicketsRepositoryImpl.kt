package com.data

import com.domain.AirTicketsRepository
import com.domain.OffersDomainEntity
import com.domain.TicketsDomainEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AirTicketsRepositoryImpl @Inject constructor(
    private val ticketsService: TicketsService,
    private val offersResponseMapper: Mapper<OffersResponse.Offer, OffersDomainEntity>
): AirTicketsRepository {
    override fun getAllTickets(): Flow<List<TicketsDomainEntity>> {
        TODO("Not yet implemented")
    }
    override fun getAllTicketsOffers(): Flow<List<TicketsDomainEntity>> {
        TODO("Not yet implemented")
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