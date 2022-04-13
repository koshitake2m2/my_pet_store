package com.github.koshitake2m2.petstorees.core.buyer

import com.github.koshitake2m2.petstorees.shared.pattern.Aggregate

case class Buyer(
    id: BuyerId,
    name: BuyerName
) extends Aggregate
