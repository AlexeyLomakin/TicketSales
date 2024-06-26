package com.data

import com.domain.OffersDomainEntity
import com.domain.TicketsDomainEntity
import javax.inject.Inject

interface Mapper<I, O> {
    fun map(input: I): O
}

class OffersMapper  @Inject constructor()  : Mapper<OffersResponse.Offer, OffersDomainEntity>  {
    override fun map(input: OffersResponse.Offer): OffersDomainEntity  {
        return OffersDomainEntity(
            id = input.id,
            title = input.title,
            town = input.town,
            price = input.price.value,
        )
    }
}


class TicketsMapper @Inject constructor() : Mapper<Tickets, TicketsDomainEntity> {
    override fun map(input: Tickets): TicketsDomainEntity {
        return TicketsDomainEntity(
            id = input.id,
            badge = input.badge,
            price = TicketsDomainEntity.Price(input.price.value),
            provider_name = input.provider_name,
            company = input.company,
            departure = TicketsDomainEntity.Departure(
                town = input.departure.town,
                date = input.departure.date,
                airport = input.departure.airport
            ),
            arrival = TicketsDomainEntity.Arrival(
                town = input.arrival.town,
                date = input.arrival.date,
                airport = input.arrival.airport
            ),
            has_transfer = input.has_transfer,
            has_visa_transfer = input.has_visa_transfer,
            luggage = TicketsDomainEntity.Luggage(input.luggage.has_luggage),
            hand_luggage = TicketsDomainEntity.HandLuggage(
                has_hand_luggage = input.hand_luggage.has_hand_luggage,
                size = input.hand_luggage.size
            ),
            is_returnable = input.is_returnable,
            is_exchangable = input.is_exchangable
        )
    }
}
