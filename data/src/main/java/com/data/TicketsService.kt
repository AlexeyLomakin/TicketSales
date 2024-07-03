package com.data

import retrofit2.Response
import retrofit2.http.GET


interface TicketsService {

    //test mosk offers
    @GET("2809ecf7-c94e-42c5-9b4c-19be8f0a0876")
    suspend fun getAllOffers(): Response<OffersResponse>

//    old mosk
//    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
//    suspend fun getAllOffers(): Response<OffersResponse>


    //test mosk tickets
    @GET("3426476f-33df-42ae-8dc5-fb2f06fb9d79")
    suspend fun getAllTickets(): Response<TicketsResponse>

//    old mosk
//    @GET("c0464573-5a13-45c9-89f8-717436748b69")
//    suspend fun getAllTickets(): Response<List<Tickets>>


    //test mosk tickets
    @GET("3f748974-7093-46e2-80b8-5874eef7cf64")
    suspend fun getAllTicketOffers(): Response<TicketsOffersResponse>

//    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
//    suspend fun getAllTicketOffers(): Response<List<TicketsOffers>>
}