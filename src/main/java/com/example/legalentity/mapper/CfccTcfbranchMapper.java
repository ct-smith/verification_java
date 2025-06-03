package com.example.legalentity.mapper;

import java.util.List;

import com.example.legalentity.example.CfccTcfbranchExample;
import com.example.legalentity.model.CfccTcfbranch;

public interface CfccTcfbranchMapper {

    List<CfccTcfbranch> selectByExample(CfccTcfbranchExample example);

}
