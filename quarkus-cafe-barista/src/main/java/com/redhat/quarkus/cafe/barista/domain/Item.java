package com.redhat.quarkus.cafe.barista.domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum Item {

    BLACK_COFFEE, COFFEE_BLACK, COFFEE_WITH_ROOM, CAPPUCCINO, ESPRESSO, ESPRESSO_DOUBLE, LATTE
}
