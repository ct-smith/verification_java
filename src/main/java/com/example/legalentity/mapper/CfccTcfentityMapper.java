package com.example.legalentity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.legalentity.example.CfccTcfentityExample;
import com.example.legalentity.model.CfccTcfentity;
import com.example.legalentity.model.CfccTcfentityKey;
import com.example.legalentity.model.CfccTcfentityWithBLOBs;

@Mapper
public interface CfccTcfentityMapper {

    List<CfccTcfentity> selectByExample(CfccTcfentityExample example);

    List<CfccTcfentityWithBLOBs> selectByExampleWithBLOBs(CfccTcfentityExample example);

    CfccTcfentityWithBLOBs selectByPrimaryKey(CfccTcfentityKey key);

    int insertSelective(CfccTcfentityWithBLOBs record);

    int updateWithVersionByPrimaryKey(@Param("version") byte[] version, @Param("record") CfccTcfentity record);
}
