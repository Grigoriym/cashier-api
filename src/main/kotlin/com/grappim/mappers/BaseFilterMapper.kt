package com.grappim.mappers

import com.grappim.data_service.model.base.BaseFilterDTO
import com.grappim.domain.model.base.BaseFilter

fun BaseFilterDTO.toDomain(): BaseFilter =
  BaseFilter(
    limit = this.limit,
    offset = this.offset,
    merchantId = this.merchantId,
    stockId = this.stockId
  )