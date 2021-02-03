package com.demo.nerdeyesem.domain.entities

enum class SortType(private val type: String) {
    COST("cost"),
    RATING("rating"),
    REAL_DISTANCE("real_distance");

    override fun toString() = type
}