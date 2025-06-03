package com.example.legalentity;

import org.springframework.stereotype.Component;

import com.example.legalentity.model.CsvCommonDto;

import lombok.RequiredArgsConstructor;

/**
 * Rating登録 帳票出力クラス
 * 
 * 機能ID:CFIC0010
 * 
 * @author wild idea
 */
@RequiredArgsConstructor
@Component
public class RatingPrint {

    /**
     * 帳票出力メソッド
     * 
     * @param printData 帳票出力対象データ
     * @param printMode プリントモード
     * @return プリントID
     */
    public String printOut(CsvCommonDto printData, Integer printMode) {

        return null;

    }
}
