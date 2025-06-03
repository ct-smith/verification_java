package com.example.legalentity.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.legalentity.model.CfccTcfdatetimeKey;
import com.example.legalentity.model.CfccTcfdatetimeWithBLOBS;

@Mapper
public interface CfcctcfdatetimeMapper {

    CfccTcfdatetimeWithBLOBS selectByPrimaryKey(CfccTcfdatetimeKey key);

}
