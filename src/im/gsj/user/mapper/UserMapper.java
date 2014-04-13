package im.gsj.user.mapper;

import im.gsj.user.dto.ShopInfo;
import im.gsj.user.dto.User;
import im.gsj.util.mapper.Mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends Mybatis{
	
	@Select("SELECT * FROM user WHERE id = #{userId}")
	User getUser(@Param("userId") String userId);
	
	@Select("SELECT u.phone,	s.`name`,	s.address FROM	USER u,	shop s WHERE	u.id = s.user_id and u.id = #{id}")
	List<ShopInfo> query(@Param("id") String id);
	
	@Insert("insert user values(#{id},#{phone},#{password})")
	void insertUser(@Param("id") String id, @Param("phone") String phone, @Param("password") String password);
	
	@Update("update user set password=#{password} where id=#{id}")
	void updateUser(@Param("id") String id, @Param("password") String password);
	
	@Delete("delete from user where id = #{id}")
	void deleteUser(@Param("id") String id);
	
}
