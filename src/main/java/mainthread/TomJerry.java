package mainthread;

import collatian.StudentPunishment;
import entity.Video;
import entity.VideoInformation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TomJerry {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("D:\\Java Project\\javaadvanced\\src\\main\\resources\\TomJerry.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        int count = 0;
        ArrayList<Video> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (count == 0) {
                count++;
                continue;
            }
            String[] splitted = line.split("\\s{2,}");
            if (splitted.length == 4) {
                String day = splitted[0];
                String id = splitted[1];
                String title = splitted[2];
                String view = splitted[3];
                Video video = new Video(day, id, title, view);
                list.add(video);
            }
            count++;
        }
        HashMap<String, VideoInformation> statistic = new HashMap<>();
        for (Video video : list) {
            int currentView = 0;
            if (statistic.containsKey(video.getId())) {
                VideoInformation videoInformation = statistic.get(video.getId());
                currentView = videoInformation.getView();
            }
            currentView += Integer.parseInt(video.getView());
            VideoInformation videoInformation = new VideoInformation(video.getId(), video.getTitle(), currentView);
            statistic.put(video.getId(), videoInformation);
        }

        for (String id :
                statistic.keySet()) {
            System.out.println("Id: " + id);
            VideoInformation videoInformation = statistic.get(id);
            System.out.println("Title: " + videoInformation.getTitle());
            System.out.println("View: " + videoInformation.getView());
        }
    }
}
