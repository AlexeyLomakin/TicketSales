package com.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTicketsOffersUseCaseImpl @Inject constructor(
    private val ticketsRepository: AirTicketsRepository
): GetAllTicketsOffersUseCase {
    override  fun invoke(): Flow<List<TicketsOffersDomainEntity>> {
        return ticketsRepository.getAllTicketsOffers()
    }
}

interface GetAllTicketsOffersUseCase {
    operator fun invoke(): Flow<List<TicketsOffersDomainEntity>>
}