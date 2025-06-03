package com.example.legalentity.mapper;

import java.util.List;

import com.example.legalentity.example.CfccTcfcbcountryExample;
import com.example.legalentity.model.CfccTcfcbcountry;
import com.example.legalentity.model.CfccTcfcbcountryKey;

public interface CfccTcfcbcountryMapper {

    List<CfccTcfcbcountry> selectByExample(CfccTcfcbcountryExample example);

    CfccTcfcbcountry selectByPrimaryKey(CfccTcfcbcountryKey key);
}
