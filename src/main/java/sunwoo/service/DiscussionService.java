package sunwoo.service;

import sunwoo.Auth;
import sunwoo.Role;
import sunwoo.domain.Band;
import sunwoo.domain.DiscussionItem;
import sunwoo.domain.Song;
import sunwoo.domain.User;
import sunwoo.repository.DiscussionRepository;
import sunwoo.util.LogHandler;

import java.util.Optional;
import java.util.Scanner;

import static sunwoo.repository.DiscussionRepository.updateDiscussionItemSession;

public class DiscussionService {

    //현재 회의록에 등록된 곡 목록 조회
    public static void listAllItem(){

        User user = Auth.getNowUser();
        Band band = user.getBand();

        System.out.println("+------------------------------------------------------------------------------------------------+");
        System.out.printf("|%-20s |%-30s |%-20s |%-20s|\n","ID","Title","Artist","writeBy");
        System.out.println("+------------------------------------------------------------------------------------------------+");
        DiscussionRepository.findAllDiscussionItem(band).stream()
                .forEach(
                        d->System.out.printf("|%-20s |%-30s |%-20s |%-20s|\n",d.getId(), d.getSong().getTitle(), d.getSong().getArtist(), d.getWriteBy().getUsername()
                ));
        System.out.println("+------------------------------------------------------------------------------------------------+");

    }

    public static void getItem(String initID, Scanner scanner) {
        String id = initID;
        while (true) {
            Optional<DiscussionItem> item = DiscussionRepository.findDiscussionItemById(id);
            if (item.isPresent()) {
                System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------+");
                System.out.printf("|%-10s |%-30s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s \n", "ID", "Title", "Vocal", "Lead G.", "Rhythm G.", "Bass", "Keyboard", "Drum");
                System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------+");
                item.stream()
                        .forEach(
                                i -> System.out.printf(
                                        "|%-10s |%-30s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s \n",
                                        i.getId(), i.getSong().getTitle(),
                                        i.getSession().get(Role.VOCAL),
                                        i.getSession().get(Role.GUITAR_LEAD),
                                        i.getSession().get(Role.GUITAR_RHYTHM),
                                        i.getSession().get(Role.BASS),
                                        i.getSession().get(Role.KEYBOARD),
                                        i.getSession().get(Role.DRUM)

                                ));
                System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------+");
                break;
            } else {
                LogHandler.warn("잘못된 ID 입니다.");
                id = scanner.nextLine();
            }
        }


    }

    public static void participateSession(String id, Role role, Scanner scanner) {
        updateDiscussionItemSession(id,role);
    }

    public static void addSongToDiscussion(String title, String artist) {
        User user = Auth.getNowUser();
        DiscussionRepository.addDiscussionItem(new DiscussionItem(user.getBand(), user, new Song(title, artist)));
    }
    //곡 등록하기

}
