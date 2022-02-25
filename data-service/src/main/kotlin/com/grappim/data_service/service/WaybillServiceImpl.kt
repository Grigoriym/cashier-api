package com.grappim.data_service.service

import com.grappim.db.entities.WaybillEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.WaybillTable
import com.grappim.domain.model.base.BaseFilter
import com.grappim.domain.model.waybill.CreateWaybill
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillStatus
import com.grappim.domain.service.WaybillService
import com.grappim.utils.WaybillDoesNotExist
import com.grappim.utils.padWithZeroes
import com.grappim.utils.toUUID
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.math.BigDecimal
import java.time.LocalDateTime

class WaybillServiceImpl : WaybillService, BaseService {

  override fun filterWaybills(
    filter: BaseFilter
  ): List<Waybill> = transaction {
    checkStockIdAndMerchantId(
      merchantId = filter.merchantId,
      stockId = filter.stockId
    )
    val waybills = WaybillEntity.find(op = {
      (WaybillTable.merchantId eq filter.merchantId.toUUID()) and
          (WaybillTable.stockId eq filter.stockId.toUUID())
    }).orderBy(WaybillTable.updatedOn to SortOrder.DESC)
      .limit(
        n = filter.limit,
        offset = filter.offset
      )
    waybills.toList().toDomain()
  }

  private fun getWaybill(
    waybillId: Long
  ): Waybill = transaction {
    val entity = WaybillEntity
      .findById(waybillId) ?: throw WaybillDoesNotExist()
    entity.toDomain()
  }

  private fun getWaybills(
    merchantId: String,
    stockId: String
  ): List<Waybill> = transaction {
    WaybillEntity.find {
      (WaybillTable.merchantId eq merchantId.toUUID()) and
          (WaybillTable.stockId eq stockId.toUUID())
    }.toList().toDomain()
  }

  override fun createWaybill(
    createWaybill: CreateWaybill
  ): Waybill = transaction {
    val newWaybill = WaybillEntity.new {
      this.merchantId = createWaybill.merchantId.toUUID()
      this.stockId = createWaybill.stockId.toUUID()

      this.createdOn = LocalDateTime.now()
      this.updatedOn = LocalDateTime.now()

      this.status = WaybillStatus.DRAFT
      this.comment = ""
      this.totalCost = BigDecimal.ZERO
      this.reservedTime = null
      this.number = getWaybills(
        merchantId = createWaybill.merchantId,
        stockId = createWaybill.stockId
      ).size.padWithZeroes(6)
    }
    newWaybill.toDomain()
  }

  override fun updateWaybill(
    newWaybill: Waybill
  ): Waybill = transaction {
    WaybillTable.update({ WaybillTable.id eq newWaybill.id }) { waybill ->
      waybill[merchantId] = newWaybill.merchantId.toUUID()
      waybill[stockId] = newWaybill.stockId.toUUID()
      waybill[updatedOn] = LocalDateTime.now()
      waybill[createdOn] = newWaybill.createdOn
      waybill[status] = newWaybill.status
      waybill[comment] = newWaybill.comment
      waybill[totalCost] = newWaybill.totalCost
      waybill[number] = newWaybill.number
    }
    getWaybill(newWaybill.id)
  }

  override fun deleteWaybill(
    id: Long
  ): Int = transaction {
    WaybillTable.deleteWhere {
      WaybillTable.id eq id
    }
  }

}