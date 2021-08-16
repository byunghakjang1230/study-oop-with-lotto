package org.byunghakjang1230.lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.byunghakjang1230.lotto.exception.DuplicateLottoNumbersException;

public class Lotto {
    public static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        validateLottoNumbersSize(lottoNumbers);
        validateDuplicateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public int matchNumberCount(final List<LottoNumber> lottoNumbers) {
        List<LottoNumber> resultLottoNumbers = new ArrayList<>(lottoNumbers);
        resultLottoNumbers.retainAll(this.lottoNumbers);
        return lottoNumbers.size();
    }

    private void validateLottoNumbersSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("입력된 로또번호가 6개가 아닙니다.");
        }
    }

    private void validateDuplicateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
            throw new DuplicateLottoNumbersException("중복되는 로또번호가 존재합니다.");
        }
    }
}
