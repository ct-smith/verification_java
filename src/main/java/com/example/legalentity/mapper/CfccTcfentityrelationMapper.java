package com.example.legalentity.mapper;

import java.util.List;

import com.example.legalentity.example.CfccTcfentityrelationExample;
import com.example.legalentity.model.CfccTcfentityrelation;

public interface CfccTcfentityrelationMapper {

    List<CfccTcfentityrelation> selectByExample(CfccTcfentityrelationExample example);

}
