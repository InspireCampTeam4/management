package sunwoo.service;

import sunwoo.util.LogHandler;
import sunwoo.repository.BandRepository;
import sunwoo.domain.Band;
import sunwoo.util.InputHandler;

import java.util.Scanner;

public class BandService {

    public static Band createBand(Scanner scanner){
        String bandName;

        //1. 밴드 이름
        while(true){
            System.out.println("밴드 이름을 입력하세요: ");
            String inputBand = scanner.nextLine().trim();
            if(!BandRepository.isBandExist(inputBand)){
                bandName = inputBand;
                break;
            }else{
                LogHandler.warn("이미 존재하는 밴드입니다!");
            }
        }
        //2. 멤버수 제한
            int inputCap = InputHandler.getIntValue(scanner);

            Band generatedBand = new Band(bandName, inputCap);
            BandRepository.addBand(generatedBand);
            return generatedBand;
    }
    public static Band findBand(Scanner scanner){
        while (true){
            System.out.println("가입하고자 하는 밴드를 선택하세요");
            System.out.println("--------------------------");
            BandRepository.findAllBands().stream()
                    .map(d->d.getBandName())
                    .forEach(System.out::println);
            System.out.println("--------------------------");
            String input = scanner.nextLine().trim();
            return BandRepository.findBandByName(input);
        }

    }
}
