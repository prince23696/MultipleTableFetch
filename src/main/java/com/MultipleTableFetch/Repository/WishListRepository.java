package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    @Query(value = "select count(id) from wish_list ", nativeQuery = true)
    public int countRecords();

    @Query(value = "SELECT EXISTS(select course_id,user_id from wish_list c where c.course_id=?1 and c.user_id=?2 )", nativeQuery = true)
    public Boolean checkRecordExistOrNot(Long courseId, int userId);

    @Query(value = "select * from wish_list c where c.course_id=?1 and c.user_id=?2 ", nativeQuery = true)
    public WishList getWishListRecord(Long courseId, int userId);

    @Query(value = "delete from wish_list c where c.user_id IN=:u", nativeQuery = true)
    public int deleteAllRecordFromWishList(@Param("u") int userId);

    @Query(value = "select * from wish_list c where c.user_id=:u", nativeQuery = true)
    public List<WishList> findFromWishListByUserId(@Param("u") int userId);
}