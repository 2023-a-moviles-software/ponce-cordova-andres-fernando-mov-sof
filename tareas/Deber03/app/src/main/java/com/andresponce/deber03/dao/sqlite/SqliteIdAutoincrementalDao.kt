package com.andresponce.deber03.dao.sqlite

import com.andresponce.deber03.util.Identificable

open class SqliteIdAutoincrementalDao<T : Identificable<Int>>(clase: Class<T>) : SqliteGenericDao<T, Int>(clase) {
}