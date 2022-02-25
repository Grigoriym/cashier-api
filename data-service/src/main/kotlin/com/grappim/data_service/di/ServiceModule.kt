package com.grappim.data_service.di

import com.grappim.data_service.service.*
import com.grappim.domain.service.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.serviceModule() {
  bind<StockService>() with singleton { StockServiceImpl() }
  bind<CashBoxService>() with singleton { CashBoxServiceImpl() }
  bind<AuthService>() with singleton { AuthServiceImpl() }
  bind<ProductService>() with singleton { ProductsServiceImpl() }
  bind<ProductCategoryService>() with singleton { ProductCategoryServiceImpl() }
  bind<WaybillService>() with singleton { WaybillServiceImpl() }
  bind<WaybillProductService>() with singleton { WaybillProductServiceImpl() }
  bind<OrderService>() with singleton { OrderServiceImpl() }
}