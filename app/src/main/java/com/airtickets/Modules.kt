package com.airtickets

import com.data.AirTicketsRepositoryImpl
import com.data.Mapper
import com.data.OffersMapper
import com.data.OffersResponse
import com.data.TicketOffersMapper
import com.data.TicketsMapper
import com.data.TicketsOffersResponse
import com.data.TicketsResponse
import com.data.TicketsService
import com.domain.AirTicketsRepository
import com.domain.GetAllOffersUseCase
import com.domain.GetAllOffersUseCaseImpl
import com.domain.GetAllTicketsOffersUseCase
import com.domain.GetAllTicketsOffersUseCaseImpl
import com.domain.GetAllTicketsUseCase
import com.domain.GetAllTicketsUseCaseImpl
import com.domain.OffersDomainEntity
import com.domain.TicketsDomainEntity
import com.domain.TicketsOffersDomainEntity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Binds
    abstract fun bindOffersMapper(offersMapper: OffersMapper): Mapper<OffersResponse.Offer, OffersDomainEntity>

    @Binds
    abstract fun bindTicketsMapper(ticketsMapper: TicketsMapper): Mapper<TicketsResponse.Tickets, TicketsDomainEntity>

    @Binds
    abstract fun bindTicketsOffersMapper(ticketOffersMapper: TicketOffersMapper): Mapper<TicketsOffersResponse.TicketsOffer, TicketsOffersDomainEntity>

    @Binds
    abstract fun bindAirTicketsOffersRepository(getAllOffers: AirTicketsRepositoryImpl): AirTicketsRepository

    @Binds
    abstract fun bindGetAllOffersUseCase(getAllOffers: GetAllOffersUseCaseImpl): GetAllOffersUseCase

    @Binds
    abstract fun bindGetAllTicketsUseCase(getAllOffers: GetAllTicketsUseCaseImpl): GetAllTicketsUseCase

    @Binds
    abstract fun bindGetAllTicketsOffersUseCase(getAllTicketsOffers: GetAllTicketsOffersUseCaseImpl): GetAllTicketsOffersUseCase

    companion object {
        @Provides
        @Singleton
        fun provideTicketsSalesService(): TicketsService {
            val baseUrl = "https://run.mocky.io/v3/"
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(TicketsService::class.java)
        }
    }
}