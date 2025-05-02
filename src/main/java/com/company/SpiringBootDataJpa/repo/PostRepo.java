package com.company.SpiringBootDataJpa.repo;

import com.company.SpiringBootDataJpa.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    //    @Query(value = "from Post p where p.userId=?1 ")  //Jpql query
//    @Query(nativeQuery = true, value = "select p.* from posts p where p.user_id=?1")// native query lang
    @Query(name = "Post.findByUserId", nativeQuery = true)
    List<Post> findAllByUserId(Integer userId);


    //    @Query("select p from Post p")  //jpql with pageable
    @Query(nativeQuery = true, value = "select * from posts",  // pageable with native query
            countQuery = "select count(*) from posts")
    Page<Post> findAllPostWithPage(Pageable pageable);

    @Query("select p from Post p where p.userId in ?1")
    List<Post> findAllByUserIds(Collection<Integer> userIds);
}
