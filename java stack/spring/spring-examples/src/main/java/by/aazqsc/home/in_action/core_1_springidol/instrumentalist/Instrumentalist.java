package by.aazqsc.home.in_action.core_1_springidol.instrumentalist;

import by.aazqsc.home.in_action.core_1_springidol.Performer;

public class Instrumentalist implements Performer {
    private String song;
    private Instrument instrument;

    public Instrumentalist() {
    }

    @Override
    public void perform(){
        System.out.print("Playing " + song + " : ");
        instrument.play();
    }

    // внедрение мелодии
    public void setSong(String song) {
        this.song = song;
    }

    public String getSong() {
        return song;
    }

    // внедрение инструмента
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public String screamSong() {
        return song;
    }

}
