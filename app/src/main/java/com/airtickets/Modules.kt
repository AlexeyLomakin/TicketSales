package com.airtickets

import com.data.AirTicketsRepositoryImpl
import com.data.Mapper
import com.data.OffersResponse
import com.data.OffersMapper
import com.data.Tickets
import com.data.TicketsMapper
import com.data.TicketsService
import com.domain.AirTicketsRepository
import com.domain.GetAllOffersUseCase
import com.domain.GetAllOffersUseCaseImpl
import com.domain.OffersDomainEntity
import com.domain.TicketsDomainEntity
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
    abstract fun bindOffersMapper(impl: OffersMapper): Mapper<OffersResponse.Offer, OffersDomainEntity>

    @Binds
    abstract fun bindTicketsMapper(impl: TicketsMapper): Mapper<Tickets, TicketsDomainEntity>

    @Binds
    abstract fun bindGetAllOffersUseCase(getAllOffers: GetAllOffersUseCaseImpl): GetAllOffersUseCase

    @Binds
    abstract fun bindAirTicketsOffersRepository(getAllOffers: AirTicketsRepositoryImpl): AirTicketsRepository

//    @Binds
//    abstract fun bindGetAllTicketsUseCase(getAllOffers: GetAllTicketsUseCaseImpl): GetAllTicketsUseCase

    companion object {
        @Provides
        @Singleton
        fun provideOffersService(): TicketsService {
            val baseUrl = "https://run.mocky.io/v3/"
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(TicketsService::class.java)
        }

//        @Provides
//        @Singleton
//        fun provideTicketOffersService(): TicketsService {
//            val baseUrl = "https://run.mocky.io/v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a"
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(baseUrl)
//                .build()
//
//            return retrofit.create(TicketsService::class.java)
//        }

//        @Provides
//        @Singleton
//        fun provideTicketsService(): TicketsService {
//            val baseUrl = "https://run.mocky.io/v3/c0464573-5a13-45c9-89f8-717436748b69"
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(baseUrl)
//                .build()
//
//            return retrofit.create(TicketsService::class.java)
//        }
    }
}