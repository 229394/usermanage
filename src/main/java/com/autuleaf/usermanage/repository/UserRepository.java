package com.autuleaf.usermanage.repository;

import com.autuleaf.usermanage.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,Long> {

    @Query("select u from UserInfo u")
    Page<UserInfo> findList(Pageable pageable);

    @Override
    Optional<UserInfo> findById(Long id);

    //@Query("select u from UserInfo  u where u.userName like concat('%',userName,'%')")
    //Page<UserInfo> findByUserNameLike(@Param("userName")String userName,Pageable pageable);

    //简单自定义查询
    UserInfo findByUserName(String userName);

    //实现更新操作
    @Query(value = "update UserInfo set userName=:userName,pwd=:pwd,age=:age,regTime=:regTime where id=:id")
    @Modifying
    void update(@Param("userName") String userName,@Param("pwd") String pwd,@Param("age") int age,@Param("regTime") Date regTime,@Param("id")long id);

    void deleteById(Long id);
}
