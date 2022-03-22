package com.grappim.data_service.service

import com.grappim.db.entities.FeatureToggleEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.FeatureToggleTable
import com.grappim.domain.model.FeatureToggle
import com.grappim.domain.service.FeatureToggleService
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.*

class FeatureToggleServiceImpl : FeatureToggleService, BaseService {

  override fun getFeatureToggle(
    merchantId: UUID,
    stockId: UUID
  ): FeatureToggle = transaction {
    val found = FeatureToggleEntity.find {
      (FeatureToggleTable.merchantId eq merchantId) and
          (FeatureToggleTable.stockId eq stockId)
    }.firstOrNull() ?: error("lol")
    found.toDomain()
  }

  override fun createFeatureToggle(
    merchantId: UUID,
    stockId: UUID
  ): FeatureToggle = transaction {
    val new = FeatureToggleEntity.new {
      this.merchantId = merchantId
      this.stockId = stockId
      this.isSalesEnabled = true
      this.isWaybillEnabled = true
    }
    new.toDomain()
  }

  override fun updateFeatureToggle(
    featureToggle: FeatureToggle
  ): FeatureToggle = transaction {
    FeatureToggleTable.update({
      (FeatureToggleTable.merchantId eq featureToggle.merchantId) and
          (FeatureToggleTable.stockId eq featureToggle.stockId)
    }
    ) { ft ->
      ft[isSalesEnabled] = isSalesEnabled
      ft[isWaybillEnabled] = isWaybillEnabled
    }
    getFeatureToggle(featureToggle.merchantId, featureToggle.stockId)
  }
}