package com.github.koshitake2m2.petstorees.core.seller

import com.github.koshitake2m2.petstorees.shared.pattern.Aggregate

case class Seller(
    id: SellerId,
    name: SellerName
) extends Aggregate
