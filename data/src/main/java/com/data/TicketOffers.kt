package com.data

data class TicketOffers (
    val id: Int,
    val title: String,
    val time_range: List<String>,
    val price: Price
) {
    data class Price(val value: Int)
}
