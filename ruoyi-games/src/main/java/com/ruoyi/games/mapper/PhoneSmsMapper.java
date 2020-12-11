package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.PhoneSms;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @date 2020/12/7 20:33
 */
@Repository
public interface PhoneSmsMapper {

    PhoneSms getPhoneSms(@Param("userID") Integer userID,
                         @Param("phoneNumber") String phoneNumber,
                         @Param("smsCode") String smsCode,
                         @Param("typeID") short typeID);

    PhoneSms getPhoneSmsByPhoneNumber(@Param("phoneNumber") String phoneNumber,
                                      @Param("smsCode") String smsCode);

    int addPhoneSMS(PhoneSms sms);
}
