package com.grappim.domain.service

import com.grappim.domain.model.order.CreateOrder

interface OrderService {

  fun createOrder(
    createOrder: CreateOrder
  ): Long

}