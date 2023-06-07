package com.nersent.pab.user.products;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nersent.pab.user.UserEntity;

@Repository
public interface UserProductLinkRepository extends JpaRepository<UserProductLinkEntity, Long> {
  Optional<List<UserProductLinkEntity>> findByUserId(Long userId);
  Optional<UserProductLinkEntity> findByUserIdAndProductId(Long userId, Long productId);
}
