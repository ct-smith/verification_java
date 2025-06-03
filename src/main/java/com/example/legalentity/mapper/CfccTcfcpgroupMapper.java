package com.example.legalentity.mapper;

import java.util.List;

import com.example.legalentity.example.CfccTcfcpgroupExample;
import com.example.legalentity.model.CfccTcfcpgroup;

public interface CfccTcfcpgroupMapper {

    List<CfccTcfcpgroup> selectByExample(CfccTcfcpgroupExample example);

}
