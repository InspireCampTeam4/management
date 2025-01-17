package sunwoo.repository;

import sunwoo.domain.User;
import sunwoo.util.LogHandler;
import sunwoo.domain.Band;

import java.util.*;

public class BandRepository {
    private static List<Band> bands = new ArrayList<>();


    public static void addBand(Band band){
        if(isBandExist(band.getBandName())){
            LogHandler.warn("이미 등록된 밴드입니다. 다른 밴드명을 입력하세요");
            return;
        }
        bands.add(band);
        LogHandler.confirm(band.getBandName() + " 활동 시작!");

    }

    public static void deleteUser(Band band){
        if(!isBandExist(band.getBandName())){
            LogHandler.warn("존재하지 않는 밴드입니다.");
            return;
        }
        bands.remove(band);
    }

    public static List<Band> findAllBands(){
        return bands;
    }

    public static Band findBandByName(String bandName){
        return bands
                .stream()
                .filter(b->b.getBandName().equals(bandName))
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("WARN: 존재하지 않는 유저입니다"));
    }

    public static boolean isBandExist(String badname) {
        Optional<Band> band = bands
                .stream()
                .filter(b->b.getBandName().equals(badname))
                .findFirst();
        if (band.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAnyBandExist() {
        return bands.size()==0 ? false : true;
    }


    private static void addBandWithoutLog(Band band){
        bands.add(band);
    }
    public static void initBands(Band[] initData){
        Arrays.asList(initData).forEach(BandRepository::addBandWithoutLog);
    }
}
