package com.domain

data class TicketsOffersDomainEntity(
    val id: Int,
    val title: String,
    val time_range: List<String>,
    val price: Price
) {
    data class Price(val value: Int)
}
