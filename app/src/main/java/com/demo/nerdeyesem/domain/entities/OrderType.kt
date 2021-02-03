package com.demo.nerdeyesem.domain.entities

enum class OrderType(private val type: String) {
    ASC("asc"),
    DESC("desc");

    override fun toString() = type
}