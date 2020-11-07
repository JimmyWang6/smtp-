package net.novaborn.dao;

import java.util.List;
import net.novaborn.entity.Mailbox;
import net.novaborn.entity.MailboxExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MailboxMapper {
    int countByExample(MailboxExample example);

    int deleteByExample(MailboxExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(Mailbox record);

    int insertSelective(Mailbox record);

    List<Mailbox> selectByExampleWithBLOBs(MailboxExample example);

    List<Mailbox> selectByExample(MailboxExample example);

    Mailbox selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") Mailbox record, @Param("example") MailboxExample example);

    int updateByExampleWithBLOBs(@Param("record") Mailbox record, @Param("example") MailboxExample example);

    int updateByExample(@Param("record") Mailbox record, @Param("example") MailboxExample example);

    int updateByPrimaryKeySelective(Mailbox record);

    int updateByPrimaryKeyWithBLOBs(Mailbox record);

    int updateByPrimaryKey(Mailbox record);

}