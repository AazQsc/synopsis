package by.aazqsc.home.in_action.core_1_springidol.onemanband;

import by.aazqsc.home.in_action.core_1_springidol.instrumentalist.Instrument;

import java.util.Map;

public class OneManBandMap <T> {
    T[] t;
    private Map<String, Instrument> instruments;

    public OneManBandMap() {
    }

    public void perform() {
        for (String key : instruments.keySet()) {
            System.out.print(key + " : ");
            Instrument instrument = instruments.get(key);
            instrument.play();
        }
    }

    /*
    * Внедрение инструментов в виде
    * отображения (Map)
    * */
    public void setInstruments(Map<String, Instrument> instruments) {
        this.instruments = instruments;
    }
}
