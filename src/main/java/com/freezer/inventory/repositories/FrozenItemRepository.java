package com.freezer.inventory.repositories;

import com.freezer.inventory.models.FrozenItem;
import org.springframework.data.repository.ListCrudRepository;

public interface FrozenItemRepository extends ListCrudRepository<FrozenItem, Long> {

}
