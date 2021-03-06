package org.byunghakjang1230.lotto.domain;

import static org.byunghakjang1230.lotto.constant.LottoRankingPolicy.findLottoRankBy;

import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

import org.byunghakjang1230.lotto.constant.LottoRankingPolicy;
import org.byunghakjang1230.lotto.exception.DuplicateLottoNumbersException;

public class Lotto implements LottoNumbers {
    public static final int LOTTO_PRICE_PER_ONE = 1_000;
    public static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        validateLottoNumbersSize(lottoNumbers);
        validateDuplicateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public LottoRankingPolicy getLottoRankBy(WinningNumbers winningNumbers) {
        return findLottoRankBy(getMatchNumberCount(winningNumbers), isMatchedBonusNumber(winningNumbers));
    }

    public int getMatchNumberCount(LottoNumbers lottoNumbers) {
        return (int) this.lottoNumbers.stream()
                .filter(lottoNumbers::isContain)
                .count();
    }

    public boolean isMatchedBonusNumber(WinningNumbers winningNumbers) {
        return this.lottoNumbers.stream()
                .anyMatch(winningNumbers::isBonusNumberMatchedBy);
    }

    @Override
    public boolean isContain(LottoNumber lottoNumber) {
        return this.lottoNumbers.stream()
                .anyMatch(lottoNumber::equals);
    }

    @Override
    public String toStringLottoNumbersWith(String prefix, String postfix, String delimiter) {
        StringJoiner stringNumber = new StringJoiner(delimiter);
        for (LottoNumber lottoNumber : lottoNumbers) {
            stringNumber.add(lottoNumber.toString());
        }
        return prefix + stringNumber.toString() + postfix;
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
