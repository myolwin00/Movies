package com.emrys.movies.data.mapper

import javax.inject.Inject

interface Mapper<I, O> {
    fun map(input: I): O
}

// Non-nullable to Non-nullable
interface ListMapper<I, O>: Mapper<List<I>?, List<O>>

class ListMapperImpl<I, O> @Inject constructor(
        private val mapper: Mapper<I, O>
) : ListMapper<I, O> {
    override fun map(input: List<I>?): List<O> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}