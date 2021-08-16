package org.byunghakjang1230.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static final int PRICE_PER_ONE_LOTTO = 1000;
    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoMachine(final LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public List<Lotto> createLottosAuto(final int price) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < calculateNumberOfBuy(price); i++) {
            lottos.add(new Lotto(lottoNumbersGenerator.generateLottoNumbers()));
        }
        return lottos;
    }

    private int calculateNumberOfBuy(int price) {
        return price / PRICE_PER_ONE_LOTTO;
    }
}