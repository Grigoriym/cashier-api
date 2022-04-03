package com.grappim.data_service.di

import com.grappim.data_service.service.*
import com.grappim.domain.service.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.serviceModule() {
  bind<StockService>() with singleton { StockServiceImpl() }
  bind<CashBoxService>() with singleton { CashBoxServiceImpl() }
  bind<AuthService>() with singleton {
    AuthServiceImpl(
      stockService = instance(),
      cashBoxService = instance(),
      featureToggleService = instance(),
      passwordManager = instance()
    )
  }
  bind<ProductService>() with singleton { ProductsServiceImpl() }
  bind<ProductCategoryService>() with singleton { ProductCategoryServiceImpl() }
  bind<WaybillService>() with singleton { WaybillServiceImpl() }
  bind<WaybillProductService>() with singleton { WaybillProductServiceImpl() }
  bind<OrderService>() with singleton { OrderServiceImpl() }
  bind<BasketService>() with singleton { BasketServiceImpl() }
  bind<FeatureToggleService>() with singleton { FeatureToggleServiceImpl() }
}