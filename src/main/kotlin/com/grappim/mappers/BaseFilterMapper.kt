package com.grappim.mappers

import com.grappim.common_data.model.base.BaseFilterDTO
import com.grappim.domain.model.base.BaseFilter

fun BaseFilterDTO.toDomain(): BaseFilter =
  BaseFilter(
    limit = this.limit,
    offset = this.offset,
    merchantId = this.merchantId,
    stockId = this.stockId,
    query = this.query
  )