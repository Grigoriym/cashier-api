package com.grappim.di

import com.grappim.db.service.*
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
}