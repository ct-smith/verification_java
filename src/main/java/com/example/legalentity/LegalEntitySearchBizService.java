package com.example.legalentity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.legalentity.bizmodel.LegalEntityRequestBizModel;
import com.example.legalentity.bizmodel.LegalEntitySearchReadResultBizModel;
import com.example.legalentity.common.SiComponentHolder;
import com.example.legalentity.common.TrBusinessException;
import com.example.legalentity.example.CfccTcfentityExample;
import com.example.legalentity.example.CfccTcfbondExample.Criteria;
import com.example.legalentity.example.CfccTcfcbcountryExample;
import com.example.legalentity.mapper.CfccTcfcbcountryMapper;
import com.example.legalentity.mapper.CfccTcfentityMapper;
import com.example.legalentity.model.CfccTcfentity;
import static com.example.legalentity.common.CommonConstants.MSG_ID_CF10A007;
import static com.example.legalentity.common.CommonConstants.RECORD_STATUS_VALID;
import static com.example.legalentity.common.CommonConstants.TEMPORARY_SIGN_VALID;

import lombok.RequiredArgsConstructor;

/**
 * LegalEntity検索サービスクラス
 * 
 * 機能ID:CFIC0003
 * 
 * @author wild idea
 */
@RequiredArgsConstructor
@Service
@Transactional
public class LegalEntitySearchBizService {

  /** SI外部提供クラス */
  private final SiComponentHolder siComponent;

  /** Legal Entity情報取得マッパー */
  private final CfccTcfentityMapper entityMapper;

  /** COUNTRY情報取得マッパー */
  private final CfccTcfcbcountryMapper countryMapper;

  /**
   * 検索条件に該当するLegalEntity情報を取得する
   * 
   * @param request 検索データ
   * @return 取得結果
   */
  public LegalEntitySearchReadResultBizModel getLegalEntityData(LegalEntityRequestBizModel request) {

    // 検索条件
    var example = new CfccTcfentityExample();
    var criteria = example.createCriteria();

    criteria.andRecordStatusEqualTo(RECORD_STATUS_VALID)
        .andTemporarySignEqualTo(TEMPORARY_SIGN_VALID);

    // COUNTRY_CODEに入力があった場合
    if (StringUtils.hasText(request.getCountryCode())) {
      // 検索条件にCOUNTRY_CODEを追加
      criteria.andHOLocationEqualTo(request.getCountryCode());
    }

    // INDUSTRY CODEに入力があった場合
    if (StringUtils.hasText(request.getIndustryCode())) {
      // 検索条件にINDUSTRY CODEを追加
      criteria.andTypeOfIndustryEqualTo(request.getIndustryCode());
    }

    // BIS_INDUSTRYに入力があった場合
    if (StringUtils.hasText(request.getBisIndustryCode())) {
      // 検索条件にBIS_INDUSTRYを追加
      criteria.andBisIndustryEqualTo(request.getBisIndustryCode());
    }

    // CREDIT_SEC_CODEに入力があった場合
    if (StringUtils.hasText(request.getCreditSectionCode())) {
      // 検索条件にCREDIT_SEC_CODEを追加
      criteria.andCreditSecCodeEqualTo(request.getCreditSectionCode());
    }

    // CP_CREDIT_TYPEに入力があった場合
    if (StringUtils.hasText(request.getCounterpartyCreditType())) {
      // 検索条件にCP_CREDIT_TYPEを追加
      criteria.andCpCreditTypeEqualTo(request.getCounterpartyCreditType());
    }

    // ISDA_MEMBERに入力があった場合
    if (StringUtils.hasText(request.getIsdaMembersSign())) {
      // 検索条件にISDA_MEMBERを追加
      criteria.andIsdaMemberEqualTo(request.getIsdaMembersSign());
    }

    // ATTENTION_SIGNに入力があった場合
    if (StringUtils.hasText(request.getAttentionSign())) {
      // 検索条件にATTENTION_SIGNを追加
      criteria.andAttentionSignEqualTo(request.getAttentionSign());
    }

    // CREATE_DATE(FROM)に入力があった場合
    if (request.getCreateDateFrom() != null) {
      // 検索条件にCREATE_DATE(FROM)を追加
      criteria.andCreateDateGreaterThanOrEqualTo(request.getCreateDateFrom());
    }

    // CREATE_DATE(TO)に入力があった場合
    if (request.getCreateDateTo() != null) {
      // 検索条件にCREATE_DATE(TO)を追加
      criteria.andCreateDateLessThanOrEqualTo(request.getCreateDateTo());
    }

    // UPDATE_DATE(FROM)に入力があった場合
    if (request.getUpdateDateFrom() != null) {
      // 検索条件にUPDATE_DATE(FROM)を追加
      criteria.andUpdateDateGreaterThanOrEqualTo(request.getUpdateDateFrom());
    }

    // UPDATE_DATE(TO)に入力があった場合
    if (request.getUpdateDateTo() != null) {
      // 検索条件にUPDATE_DATE(TO)を追加
      criteria.andUpdateDateLessThanOrEqualTo(request.getUpdateDateTo());
    }

    // AREA_CODEに入力があった場合
    if (StringUtils.hasText(request.getAreaCode())) {
      var countryExample = new CfccTcfcbcountryExample();
      var countryCriteria = countryExample.createCriteria();
      // 取得条件
      countryCriteria.andAreaCodeEqualTo(request.getAreaCode())
          .andRecordStatusEqualTo(RECORD_STATUS_VALID);

      // COUNTRY情報取得
      var countryData = countryMapper.selectByExample(countryExample);

      // 取得できた場合
      if (!countryData.isEmpty()) {
        var locationList = new ArrayList<String>();
        countryData.forEach(country -> {
          locationList.add(country.getCountryCode());
        });

        criteria.andHOLocationEqualTo(locationList);

        // 取得件数が0件の場合
      } else {
        throw new TrBusinessException(siComponent.getMessageSourceAccessor(),
            MSG_ID_CF10A007);
      }
    }
  }

  // Legal Entity情報取得
  var cfccTcfentityList = entityMapper.selectByExample(example);

  // 取得件数が0件の場合、
  if(cfccTcfentityList.isEmpty())
  {
    // TrBusinessExceptionをスローする
    throw new TrBusinessException(siComponent.getMessageSourceAccessor(),
        MSG_ID_CF10A007);
  }

  // LegalEntitySearchReadDataに取得したlegalEntityIdとlegalEntityNameShortをセットする

  // LegalEntitySearchReadResultBizModelにdataNumberとLegalEntitySearchReadDataをセットして返却

  return null;
}}
