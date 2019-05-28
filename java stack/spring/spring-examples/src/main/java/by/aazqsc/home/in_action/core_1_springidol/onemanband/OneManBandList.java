package by.aazqsc.home.in_action.core_1_springidol.onemanband;

import by.aazqsc.home.in_action.core_1_springidol.Performer;
import by.aazqsc.home.in_action.core_1_springidol.instrumentalist.Instrument;

import java.util.Collection;

public class OneManBandList implements Performer {
    private Collection<Instrument> instruments;

    public OneManBandList() {
    }

    public void perform() {
        for (Instrument instrument : instruments) {
            instrument.play();
        }
    }

    // Внедрение коллекции инструментов
    public void setInstruments(Collection<Instrument> instruments) {
        this.instruments = instruments;
    }
}
