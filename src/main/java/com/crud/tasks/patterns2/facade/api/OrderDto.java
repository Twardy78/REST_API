package com.crud.tasks.patterns2.facade.api;

import java.util.*;

public class OrderDto {
    private final List<ItemDto> items = new ArrayList<>();
    public void addItem(final ItemDto item) { items.add(item); }
    public List<ItemDto> getItems() {
        return items;
    }
}
