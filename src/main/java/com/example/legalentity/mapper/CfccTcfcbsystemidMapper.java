package com.example.legalentity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.legalentity.example.CfccTcfcbsystemidExample;
import com.example.legalentity.model.CfccTcfcbsystemid;

@Mapper
public interface CfccTcfcbsystemidMapper {

    List<CfccTcfcbsystemid> selectByExample(CfccTcfcbsystemidExample example);
}
