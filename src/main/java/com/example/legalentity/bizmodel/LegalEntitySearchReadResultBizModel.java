package com.example.legalentity.bizmodel;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * LegalEntity検索モデルクラス
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data
public class LegalEntitySearchReadResultBizModel {

  /* リーガルエンティティ検索件数 */
  private	Integer	dataNumber;	

  /* リーガルエンティティ検索モデル */
  private List<LegalEntitySearchReadData> legalEntitySearchReadDatas;

}
