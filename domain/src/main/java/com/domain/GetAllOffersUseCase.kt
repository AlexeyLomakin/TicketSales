package com.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllOffersUseCaseImpl @Inject constructor(
    private val ticketsRepository: AirTicketsRepository
): GetAllOffersUseCase {
    override  fun invoke(): Flow<List<OffersDomainEntity>> {
        return ticketsRepository.getAllOffers()
    }
}

interface GetAllOffersUseCase {
     operator fun invoke(): Flow<List<OffersDomainEntity>>
}