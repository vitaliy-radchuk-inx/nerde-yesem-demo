package com.demo.nerdeyesem.domain.entities

enum class EntityType(private val type: String) {
    NONE("none"),
    CITY("city"),
    SUB_ZONE("subzone"),
    ZONE("zone"),
    LANDMARK("landmark"),
    METRO("metro"),
    GROUP("group");

    override fun toString() = type
}
