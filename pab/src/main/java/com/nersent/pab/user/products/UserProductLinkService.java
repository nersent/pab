package com.nersent.pab.user.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProductLinkService {
  @Autowired
  private UserProductLinkRepository userProductLinkRepository;

  public UserProductLinkEntity create(Long userId, Long productId) {
    UserProductLinkEntity userProductLink = new UserProductLinkEntity(userId, productId);
    return userProductLinkRepository.save(userProductLink);
  }

  public Optional<List<UserProductLinkEntity>> getListForUser(Long userId) {
    return userProductLinkRepository.findByUserId(userId);
  }

  public Optional<UserProductLinkEntity> get(Long userId, Long productId) {
    return userProductLinkRepository.findByUserIdAndProductId(userId, productId);
  }

  public List<UserProductLinkEntity> getAll() {
    return userProductLinkRepository.findAll();
  } 

  public void save(UserProductLinkEntity userProductLink) {
    userProductLinkRepository.save(userProductLink);
  }

  public void delete(UserProductLinkEntity userProductLink) {
    userProductLinkRepository.delete(userProductLink);
  }
}
