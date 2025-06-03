package com.example.legalentity.mapper;

import java.util.List;

import com.example.legalentity.example.CfccTcfbondExample;
import com.example.legalentity.model.CfccTcfbond;

public interface CfccTcfbondMapper {

    List<CfccTcfbond> selectByExample(CfccTcfbondExample example);

}
