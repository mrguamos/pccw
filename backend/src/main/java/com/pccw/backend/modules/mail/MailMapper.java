package com.pccw.backend.modules.mail;

import org.apache.ibatis.annotations.Param;

public interface MailMapper {

    long insert(@Param("entity") Mail mail);

}
