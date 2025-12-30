package com.freezer.inventory.services;

import com.freezer.inventory.models.FrozenItem;
import com.freezer.inventory.repositories.FrozenItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FrozenItemService {
  @Autowired
  FrozenItemRepository frozenItemRepository;

  public List<FrozenItem> getAll(){
    return frozenItemRepository.findAll();
  }

  public Optional<FrozenItem> getById(Long id) {
    return frozenItemRepository.findById(id);
  }

  public FrozenItem save(FrozenItem feedItem) {
    if (feedItem.getId() == null) {
      feedItem.setFrozenAt(LocalDate.now());
    }

    return frozenItemRepository.save(feedItem);
  }
}
