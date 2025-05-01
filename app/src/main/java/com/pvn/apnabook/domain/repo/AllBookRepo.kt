package com.pvn.apnabook.domain.repo

import com.pvn.apnabook.common.BookCategoryModel
import com.pvn.apnabook.common.BookModel
import com.pvn.apnabook.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AllBookRepo {

    fun getAllBooks(): Flow<ResultState<List<BookModel>>>
    fun getAllCategory(): Flow<ResultState<List<BookCategoryModel>>>
    fun getAllBookByCategory(category: String): Flow<ResultState<List<BookModel>>>

}