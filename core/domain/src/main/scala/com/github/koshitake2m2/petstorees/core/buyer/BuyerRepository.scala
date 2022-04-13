package com.github.koshitake2m2.petstorees.core.buyer

import com.github.koshitake2m2.petstorees.shared.pattern.Repository

trait BuyerRepository[F[_]] extends Repository[F, Buyer, BuyerId]
