package com.github.koshitake2m2.petstorees.core.seller

import com.github.koshitake2m2.petstorees.shared.pattern.Repository

trait SellerRepository[F[_]] extends Repository[F, Seller, SellerId]
