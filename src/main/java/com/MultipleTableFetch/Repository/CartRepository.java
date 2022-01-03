package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.Cart;
import com.MultipleTableFetch.Entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "select count(id) from cart ", nativeQuery = true)
    public int countRecords();

    @Query(value = "SELECT EXISTS(select course_id,user_id from cart c where c.course_id=?1 and c.user_id=?2 )", nativeQuery = true)
    public Boolean checkRecordExistOrNot(Long courseId, int userId);

    @Query(value = "select * from cart c where c.course_id=?1 and c.user_id=?2", nativeQuery = true)
    public Cart getCartRecord(Long courseId, int userId);

    @Query(value = "delete from cart c where c.user_id=:u RETURNING *", nativeQuery = true)
    public List<Cart> deleteAllRecordFromCart(@Param("u") int userId);

    @Query(value = "select * from cart c where c.user_id=:u", nativeQuery = true)
    public List<Cart> findFromCartByUserId(@Param("u") int userId);
}