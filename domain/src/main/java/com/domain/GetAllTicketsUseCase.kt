package com.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllTicketsUseCaseImpl @Inject constructor(
    private val ticketsRepository: AirTicketsRepository
): GetAllTicketsUseCase {
    override suspend fun invoke(): Flow<List<TicketsDomainEntity>> {
        return ticketsRepository.getAllTickets()
    }
}

interface GetAllTicketsUseCase {
    suspend operator fun invoke(): Flow<List<TicketsDomainEntity>>
}