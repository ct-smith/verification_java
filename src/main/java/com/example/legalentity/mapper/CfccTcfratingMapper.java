package com.example.legalentity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.legalentity.example.CfccTcfratingExample;
import com.example.legalentity.model.CfccTcfrating;
import com.example.legalentity.model.CfccTcfratingWithBLOBs;

@Mapper
public interface CfccTcfratingMapper {

    List<CfccTcfrating> selectByExample(CfccTcfratingExample example);

    List<CfccTcfratingWithBLOBs> selectByExampleWithBLOBs(CfccTcfratingExample example);

    int insert(CfccTcfratingWithBLOBs record);

    int updateWithVersionByPrimaryKeyWithBLOBs(@Param("version") byte[] version,
            @Param("record") CfccTcfratingWithBLOBs record);
}
