package net.novaborn.dao;

import java.util.List;
import net.novaborn.entity.Mail;
import net.novaborn.entity.MailExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MailMapper {
    int countByExample(MailExample example);

    int deleteByExample(MailExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(Mail record);

    int insertSelective(Mail record);

    List<Mail> selectByExampleWithBLOBs(MailExample example);

    List<Mail> selectByExample(MailExample example);

    Mail selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") Mail record, @Param("example") MailExample example);

    int updateByExampleWithBLOBs(@Param("record") Mail record, @Param("example") MailExample example);

    int updateByExample(@Param("record") Mail record, @Param("example") MailExample example);

    int updateByPrimaryKeySelective(Mail record);

    int updateByPrimaryKeyWithBLOBs(Mail record);

    int updateByPrimaryKey(Mail record);
}