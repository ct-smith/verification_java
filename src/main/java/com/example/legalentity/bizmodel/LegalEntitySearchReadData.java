package com.example.legalentity.bizmodel;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * LegalEntity検索モデルクラスの受け渡しデータ
 * 
 * @author wild idea
 */
@Accessors(chain = true)
@Data
public class LegalEntitySearchReadData {

    /** リーガルエンティティコード */
    private String legalEntityId;

    /* リーガルエンティティ略称 */
    private String legalEntityNameShort;
  
}
