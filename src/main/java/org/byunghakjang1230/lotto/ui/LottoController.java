package org.byunghakjang1230.lotto.ui;

import java.util.Scanner;

import org.byunghakjang1230.lotto.domain.*;

public class LottoController {
    private final LottoMachine lottoMachine;
    private final InputView inputView;
    private final ResultView resultView;

    public LottoController() {
        this.lottoMachine = new LottoMachine(new AutoLottoNumbersGenerator());
        this.inputView = new InputView(new Scanner(System.in));
        this.resultView = new ResultView();
    }

    public void runLottoGame() {
        Lottos lottos = this.lottoMachine.createLottosAutomatically(inputView.showInputPriceComment());
        resultView.showLottoNumbers(lottos);
        WinningResultStatistics winningResultStatistics =
                lottos.makeWinningResultStatistics(WinningNumbers.of(inputView.showInputLastWeekWinningNumbers()));
        resultView.showWinningResultStatistics(winningResultStatistics);
    }
}
