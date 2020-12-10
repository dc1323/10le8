package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.AccountsInfoBank;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @date 2020/12/7 20:45
 */
@Repository
public interface AccountsInfoBankMapper {

    List<AccountsInfoBank> getUserBank(AccountsInfoBank info);


}
