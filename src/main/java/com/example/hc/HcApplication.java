package com.example.hc;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.hc.constants.ItemConstants.TO_DELETE_LOW;
import static com.example.hc.constants.ItemMsg.ITEMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HcApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcApplication.class, args);
    }

    public static void main1(String[] args) {
        List<Integer> list = List.of(
                1572979,
                1573024,
                1573069,
                1573114,
                1572980,
                1573070,
                1573115,
                1572994,
                1573039,
                1573084,
                1573129,
                1572995,
                1573040,
                1573085,
                1573130,
                1573009,
                1573054,
                1573099,
                1573144,
                1573010,
                1573055,
                1573100,
                1573145,
                1572889,
                1572904,
                1572919,
                1572934,
                1572949,
                1572964,
                1880,
                1881,
                1882,
                1883,
                1912,
                1913,
                1914,
                1915,
                115,
                160,
                205,
                250,
                116,
                161,
                206,
                251,
                130,
                175,
                220,
                265,
                131,
                176,
                221,
                266,
                145,
                190,
                235,
                280,
                146,
                191,
                236,
                281,
                25,
                40,
                55,
                70,
                85,
                100,
                1880,
                1881,
                1882,
                1883,
                1912,
                1913,
                1914,
                1915,
                437,
                439,
                1208,
                1209,
                1210,
                2380,
                2381,
                639,
                1216,
                3598,
                3193,
                3194,
                1448,
                1454,
                1457,
                1460,
                2213,
                2218,
                2223,
                2228,
                914,
                1268,
                3341,
                3342,
                3599,
                3599,
                3600,
                3601,
                3602,
                3603,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                560,
                3109,
                3109,
                3109,
                3109,
                3109,
                3109,
                3110,
                3110,
                3110,
                3110,
                3110,
                3110,
                1,
                2,
                3654,
                3655,
                3656,
                3657,
                560,
                3979,
                3978,
                3977,
                3976,
                3980,
                3980,
                3980,
                3980,
                3981,
                3981,
                3981,
                3981,
                3982,
                3982,
                3982,
                3982,
                3983,
                3983,
                3983,
                3983,
                1214,
                1215
        );

        final Set<Integer> listIds = list.stream().map(x -> {
            if (x > 524287 && x < 528385) {
                return x - 4096 * 128;
            } else if (x > 1572863 && x < 1576961) {
                return x - 4096 * 384;
            } else {
                return x;
            }
        }).collect(Collectors.toSet());

        listIds.stream().sorted().forEachOrdered(x -> {
            String itemMsg = ITEMS.stream().filter(s -> s.contains("\"item" + x + "\"")).findFirst().orElse("");
            String itemName = itemMsg.equals("") ? "" : itemMsg.substring(itemMsg.indexOf("cont=\"") + 6, itemMsg.lastIndexOf("\""));
            System.out.println(itemMsg);
        });
        System.out.println();
    }

}
