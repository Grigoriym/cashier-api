package com.grappim.di

import com.grappim.service.AuthService
import com.grappim.service.CashBoxService
import com.grappim.service.StockService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.serviceModule() {
    bind<StockService>() with singleton { StockService() }
    bind<CashBoxService>() with singleton { CashBoxService() }
    bind<AuthService>() with singleton { AuthService() }
}