package com.example.hc.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 01.02.2023
 */
@Data
@AllArgsConstructor
public final class NewCharSkills {

    public static SkillBoundaries type1Boundaries = new SkillBoundaries(0, 31);
    public static SkillBoundaries type2Boundaries = new SkillBoundaries(32, 66);
    public static SkillBoundaries type3Boundaries = new SkillBoundaries(82, 101);
    public static SkillBoundaries type4Boundaries = new SkillBoundaries(67, 81);
    public static SkillBoundaries type4Boundaries_2 = new SkillBoundaries(104, 109);

    public static Set<Integer> ignoredSlotIds = new HashSet<>() {{
        add(104);
        add(105);
        add(106);
        add(107);
        add(108);
        add(109);
        add(68);
        add(69);
        add(70);
        add(71);
        add(72);
        add(73);
        add(74);
        add(75);
        add(78);
        add(79);
        add(80);
        add(81);
    }};

    public static final Map<Integer, Integer> oldNewSkillMapping = new HashMap<>() {
        {
            put(76, 4);
            put(77, 5);
            put(78, 6);
            put(79, 7);
            put(100, 8);
            put(171, 9);
            put(172, 12);
            put(173, 14);
            put(174, 15);
            put(175, 18);
            put(176, 19);
            put(177, 21);
            put(178, 26);
            put(275, 27);
            put(276, 28);
            put(277, 39);
            put(300, 40);
            put(301, 41);
            put(302, 42);
            put(303, 51);
            put(304, 52);
            put(305, 53);
            put(306, 54);
            put(307, 121);
            put(308, 132);
            put(310, 133);
            put(311, 136);
            put(312, 137);
            put(313, 150);
            put(314, 154);
            put(370, 200);
            put(371, 201);
            put(372, 239);
            put(373, 334);
            put(374, 335);
            put(375, 336);
            put(376, 337);
            put(377, 338);
            put(424, 339);
        }
    };

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkillBoundaries {
        private int from;
        private int to;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkillSlotLvl {
        private int id;
        private int slotId;
        private int lvl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkillLvl {
        private int id;
        private int lvl;
    }

    public static List<SkillSlotLvl> FG_4 = new ArrayList<>(Arrays.asList(
            new SkillSlotLvl(474, 104, 1),
            new SkillSlotLvl(319, 105, 1),
            new SkillSlotLvl(320, 106, 1),
            new SkillSlotLvl(321, 107, 1),
            new SkillSlotLvl(322, 108, 1),
            new SkillSlotLvl(323, 109, 1),
            new SkillSlotLvl(330, 67, 1),
            new SkillSlotLvl(420, 68, 1),
            new SkillSlotLvl(452, 69, 1),
            new SkillSlotLvl(333, 70, 1),
            new SkillSlotLvl(419, 71, 1),
            new SkillSlotLvl(310, 72, 1),
            new SkillSlotLvl(311, 73, 1),
            new SkillSlotLvl(318, 74, 1),
            new SkillSlotLvl(313, 75, 1),
            new SkillSlotLvl(304, 78, 1)
    ));
    public static List<SkillSlotLvl> GL_4 = new ArrayList<>(Arrays.asList(
            new SkillSlotLvl(474, 104, 1),
            new SkillSlotLvl(325, 105, 1),
            new SkillSlotLvl(326, 106, 1),
            new SkillSlotLvl(327, 107, 1),
            new SkillSlotLvl(328, 108, 1),
            new SkillSlotLvl(329, 109, 1),
            new SkillSlotLvl(420, 68, 1),
            new SkillSlotLvl(452, 69, 1),
            new SkillSlotLvl(333, 70, 1),
            new SkillSlotLvl(419, 71, 1),
            new SkillSlotLvl(305, 72, 1),
            new SkillSlotLvl(306, 73, 1),
            new SkillSlotLvl(324, 74, 1),
            new SkillSlotLvl(312, 75, 1),
            new SkillSlotLvl(300, 78, 1),
            new SkillSlotLvl(301, 79, 1),
            new SkillSlotLvl(302, 80, 1),
            new SkillSlotLvl(303, 81, 1)
    ));

    public static List<SkillSlotLvl> BL_4 = new ArrayList<>(Arrays.asList(
            new SkillSlotLvl(474, 104, 1),
            new SkillSlotLvl(486, 105, 1),
            new SkillSlotLvl(487, 106, 1),
            new SkillSlotLvl(488, 107, 1),
            new SkillSlotLvl(489, 108, 1),
            new SkillSlotLvl(490, 109, 1),
            new SkillSlotLvl(420, 68, 1),
            new SkillSlotLvl(452, 69, 1),
            new SkillSlotLvl(333, 70, 1),
            new SkillSlotLvl(419, 71, 1),
            new SkillSlotLvl(342, 72, 1),
            new SkillSlotLvl(343, 73, 1),
            new SkillSlotLvl(476, 74, 1),
            new SkillSlotLvl(402, 75, 1),
            new SkillSlotLvl(384, 78, 1),
            new SkillSlotLvl(385, 79, 1),
            new SkillSlotLvl(386, 80, 1),
            new SkillSlotLvl(387, 81, 1)
    ));

    public static List<SkillSlotLvl> WI_4 = new ArrayList<>(Arrays.asList(
            new SkillSlotLvl(474, 104, 1),
            new SkillSlotLvl(491, 105, 1),
            new SkillSlotLvl(492, 106, 1),
            new SkillSlotLvl(493, 107, 1),
            new SkillSlotLvl(494, 108, 1),
            new SkillSlotLvl(495, 109, 1),
            new SkillSlotLvl(420, 68, 1),
            new SkillSlotLvl(452, 69, 1),
            new SkillSlotLvl(333, 70, 1),
            new SkillSlotLvl(419, 71, 1),
            new SkillSlotLvl(344, 72, 1),
            new SkillSlotLvl(345, 73, 1),
            new SkillSlotLvl(477, 74, 1),
            new SkillSlotLvl(404, 75, 1)
    ));
    public static List<SkillSlotLvl> FB_4 = new ArrayList<>(Arrays.asList(
            new SkillSlotLvl(474, 104, 1),
            new SkillSlotLvl(506, 105, 1),
            new SkillSlotLvl(507, 106, 1),
            new SkillSlotLvl(508, 107, 1),
            new SkillSlotLvl(509, 108, 1),
            new SkillSlotLvl(510, 109, 1),
            new SkillSlotLvl(420, 68, 1),
            new SkillSlotLvl(452, 69, 1),
            new SkillSlotLvl(333, 70, 1),
            new SkillSlotLvl(419, 71, 1),
            new SkillSlotLvl(350, 72, 1),
            new SkillSlotLvl(351, 73, 1),
            new SkillSlotLvl(480, 74, 1),
            new SkillSlotLvl(410, 75, 1),
            new SkillSlotLvl(464, 78, 1),
            new SkillSlotLvl(465, 79, 1),
            new SkillSlotLvl(466, 80, 1),
            new SkillSlotLvl(467, 81, 1)
    ));
    public static List<SkillSlotLvl> FS_4 = new ArrayList<>(Arrays.asList(
            new SkillSlotLvl(474, 104, 1),
            new SkillSlotLvl(501, 105, 1),
            new SkillSlotLvl(502, 106, 1),
            new SkillSlotLvl(503, 107, 1),
            new SkillSlotLvl(504, 108, 1),
            new SkillSlotLvl(505, 109, 1),
            new SkillSlotLvl(332, 67, 1),
            new SkillSlotLvl(420, 68, 1),
            new SkillSlotLvl(452, 69, 1),
            new SkillSlotLvl(333, 70, 1),
            new SkillSlotLvl(419, 71, 1),
            new SkillSlotLvl(348, 72, 1),
            new SkillSlotLvl(349, 73, 1),
            new SkillSlotLvl(479, 74, 1),
            new SkillSlotLvl(408, 75, 1),
            new SkillSlotLvl(388, 78, 1),
            new SkillSlotLvl(389, 79, 1),
            new SkillSlotLvl(390, 80, 1)
    ));
    public static List<SkillSlotLvl> FA_4 = new ArrayList<>(Arrays.asList(
            new SkillSlotLvl(474, 104, 1),
            new SkillSlotLvl(496, 105, 1),
            new SkillSlotLvl(497, 106, 1),
            new SkillSlotLvl(498, 107, 1),
            new SkillSlotLvl(499, 108, 1),
            new SkillSlotLvl(500, 109, 1),
            new SkillSlotLvl(331, 67, 1),
            new SkillSlotLvl(420, 68, 1),
            new SkillSlotLvl(452, 69, 1),
            new SkillSlotLvl(333, 70, 1),
            new SkillSlotLvl(419, 71, 1),
            new SkillSlotLvl(346, 72, 1),
            new SkillSlotLvl(347, 73, 1),
            new SkillSlotLvl(478, 74, 1),
            new SkillSlotLvl(406, 75, 1),
            new SkillSlotLvl(391, 78, 1),
            new SkillSlotLvl(392, 79, 1),
            new SkillSlotLvl(393, 80, 1),
            new SkillSlotLvl(394, 81, 1)
    ));
    public static List<SkillSlotLvl> WA_4 = new ArrayList<>(Arrays.asList(
            new SkillSlotLvl(474, 104, 1),
            new SkillSlotLvl(481, 105, 1),
            new SkillSlotLvl(482, 106, 1),
            new SkillSlotLvl(483, 107, 1),
            new SkillSlotLvl(484, 108, 1),
            new SkillSlotLvl(485, 109, 1),
            new SkillSlotLvl(420, 68, 1),
            new SkillSlotLvl(452, 69, 1),
            new SkillSlotLvl(333, 70, 1),
            new SkillSlotLvl(419, 71, 1),
            new SkillSlotLvl(340, 72, 1),
            new SkillSlotLvl(341, 73, 1),
            new SkillSlotLvl(475, 74, 1),
            new SkillSlotLvl(400, 75, 1),
            new SkillSlotLvl(380, 78, 1),
            new SkillSlotLvl(381, 79, 1),
            new SkillSlotLvl(382, 80, 1),
            new SkillSlotLvl(383, 81, 1)
    ));
    public static List<SkillLvl> GL_1_2 = new ArrayList<>(Arrays.asList(
            new SkillLvl(208, 9),
            new SkillLvl(374, 20),
            new SkillLvl(177, 20),
            new SkillLvl(179, 20),
            new SkillLvl(209, 9))
    );
    public static List<SkillLvl> FG_1_2 = new ArrayList<>(Arrays.asList(new SkillLvl(208, 9), new SkillLvl(209, 9)));
    public static List<SkillLvl> WA_1_2 = new ArrayList<>(Arrays.asList(
            new SkillLvl(208, 9),
            new SkillLvl(2, 20),
            new SkillLvl(3, 20),
            new SkillLvl(1, 20),
            new SkillLvl(209, 9)
    )
    );
    public static List<Integer> GL_1 = new ArrayList<>(Arrays.asList(
            395,
            375,
            396,
            398,
            293,
            399,
            352,
            353,
            354,
            355,
            356,
            357,
            358,
            359,
            368,
            369,
            370,
            371,
            372,
            373
    ));
    public static List<Integer> GL_2 = new ArrayList<>(Arrays.asList(
            178,
            181,
            298,
            376,
            378,
            397,
            377,
            115,
            116,
            117,
            118,
            119,
            120,
            122,
            123,
            124,
            125,
            126,
            127
    ));
    public static List<Integer> FG_1 = List.of();
    public static List<Integer> FG_2 = new ArrayList<>(Arrays.asList(
            76,
            88,
            96,
            97,
            77,
            98,
            78,
            440,
            441,
            442,
            443,
            444,
            447,
            448,
            449,
            450,
            451,
            79,
            80,
            90,
            171,
            172,
            173,
            174,
            81,
            82,
            83,
            84,
            91,
            317,
            89,
            94,
            85,
            86,
            87
    ));
    public static List<Integer> WA_1 = new ArrayList<>(Arrays.asList(
            10,
            203,
            22,
            23,
            30,
            204,
            68,
            38,
            216,
            43,
            205,
            206,
            34,
            202,
            210,
            55,
            217,
            218,
            219,
            63,
            220,
            221,
            72,
            4
    ));
    public static List<Integer> WA_2 = new ArrayList<>(Arrays.asList(
            115,
            116,
            117,
            118,
            119,
            120,
            122,
            123,
            124,
            125,
            126,
            127
    ));

    public static List<SkillLvl> WA_3 = new ArrayList<>(Arrays.asList(
            new SkillLvl(40, 10),
            new SkillLvl(41, 10),
            new SkillLvl(42, 8),
            new SkillLvl(51, 8),
            new SkillLvl(53, 9),
            new SkillLvl(54, 9),
            new SkillLvl(133, 3),
            new SkillLvl(136, 3),
            new SkillLvl(150, 3),
            new SkillLvl(154, 3),
            new SkillLvl(223, 1),
            new SkillLvl(230, 1),
            new SkillLvl(203, 1),
            new SkillLvl(204, 1),
            new SkillLvl(205, 1),
            new SkillLvl(216, 1)
    ));

    public static List<SkillLvl> BL_1_2 = new ArrayList<>(Arrays.asList(
            new SkillLvl(208, 9),
            new SkillLvl(2, 20),
            new SkillLvl(1, 20),
            new SkillLvl(16, 20),
            new SkillLvl(209, 9))
    );
    public static List<Integer> BL_1 = new ArrayList<>(Arrays.asList(
            203,
            22,
            24,
            31,
            204,
            20,
            68,
            38,
            211,
            44,
            70,
            205,
            212,
            35,
            202,
            210,
            56,
            213,
            214,
            64,
            207,
            215,
            73,
            5
    ));

    public static List<Integer> BL_2 = new ArrayList<>(Arrays.asList(
            115,
            116,
            117,
            118,
            119,
            120,
            122,
            123,
            124,
            125,
            126,
            127
    ));
    public static List<SkillLvl> BL_3 = new ArrayList<>(Arrays.asList(
            new SkillLvl(40, 10),
            new SkillLvl(41, 10),
            new SkillLvl(42, 8),
            new SkillLvl(51, 8),
            new SkillLvl(53, 9),
            new SkillLvl(54, 9),
            new SkillLvl(133, 3),
            new SkillLvl(136, 3),
            new SkillLvl(150, 3),
            new SkillLvl(154, 3),
            new SkillLvl(223, 1),
            new SkillLvl(230, 1),
            new SkillLvl(203, 1),
            new SkillLvl(204, 1),
            new SkillLvl(205, 1),
            new SkillLvl(211, 1),
            new SkillLvl(212, 1)
    ));

    public static List<SkillLvl> FA_3 = new ArrayList<>(Arrays.asList(
            new SkillLvl(40, 10),
            new SkillLvl(41, 10),
            new SkillLvl(51, 8),
            new SkillLvl(52, 8),
            new SkillLvl(53, 9),
            new SkillLvl(54, 9),
            new SkillLvl(133, 3),
            new SkillLvl(136, 3),
            new SkillLvl(137, 3),
            new SkillLvl(154, 3),
            new SkillLvl(223, 1),
            new SkillLvl(230, 1),
            new SkillLvl(240, 1),
            new SkillLvl(246, 1),
            new SkillLvl(272, 1),
            new SkillLvl(273, 1)
    ));

    public static List<Integer> FA_2 = new ArrayList<>(Arrays.asList(
            148,
            149,
            242,
            238,
            439,
            440,
            441,
            442,
            443,
            444,
            151,
            241,
            446,
            447,
            448,
            449,
            450,
            451,
            152,
            40,
            153,
            245,
            272,
            155,
            273,
            247,
            156,
            168,
            274,
            170,
            12
    ));

    public static List<SkillLvl> FA_1_2 = new ArrayList<>(Arrays.asList(new SkillLvl(208, 9), new SkillLvl(209, 9)));
    public static List<Integer> FA_1 = List.of();
    public static List<Integer> WI_1 = List.of();
    public static List<SkillLvl> WI_1_2 = new ArrayList<>(Arrays.asList(new SkillLvl(208, 9), new SkillLvl(209, 9)));
    public static List<Integer> WI_2 = new ArrayList<>(Arrays.asList(
            128, 129,
            231,
            115,
            116,
            117,
            118,
            119,
            120,
            122,
            123,
            124,
            125,
            126,
            127,
            135,
            232,
            233,
            242,
            138,
            234,
            236,
            140,
            229,
            139,
            235,
            141,
            228,
            167,
            226,
            227,
            169,
            9
    ));

    public static List<SkillLvl> WI_3 = new ArrayList<>(Arrays.asList(
            new SkillLvl(40, 10),
            new SkillLvl(41, 10),
            new SkillLvl(51, 8),
            new SkillLvl(52, 8),
            new SkillLvl(53, 9),
            new SkillLvl(54, 9),
            new SkillLvl(133, 3),
            new SkillLvl(136, 3),
            new SkillLvl(137, 3),
            new SkillLvl(154, 3),
            new SkillLvl(223, 1),
            new SkillLvl(230, 1)
    ));

    public static List<SkillLvl> FG_3 = new ArrayList<>(Arrays.asList(
            new SkillLvl(40, 10),
            new SkillLvl(41, 10),
            new SkillLvl(51, 8),
            new SkillLvl(52, 8),
            new SkillLvl(53, 9),
            new SkillLvl(54, 9),
            new SkillLvl(133, 3),
            new SkillLvl(136, 3),
            new SkillLvl(137, 3),
            new SkillLvl(154, 3),
            new SkillLvl(223, 1),
            new SkillLvl(230, 1),
            new SkillLvl(95, 1)
    ));

    public static List<SkillLvl> FB_3 = new ArrayList<>(Arrays.asList(
            new SkillLvl(40, 10),
            new SkillLvl(41, 10),
            new SkillLvl(42, 8),
            new SkillLvl(51, 8),
            new SkillLvl(53, 9),
            new SkillLvl(54, 9),
            new SkillLvl(133, 3),
            new SkillLvl(136, 3),
            new SkillLvl(150, 3),
            new SkillLvl(154, 3),
            new SkillLvl(223, 1),
            new SkillLvl(230, 1),
            new SkillLvl(253, 1),
            new SkillLvl(254, 1),
            new SkillLvl(255, 1)
    ));
    public static List<SkillLvl> GL_3 = new ArrayList<>(Arrays.asList(
            new SkillLvl(40, 10),
            new SkillLvl(41, 10),
            new SkillLvl(42, 8),
            new SkillLvl(51, 8),
            new SkillLvl(53, 9),
            new SkillLvl(54, 9),
            new SkillLvl(133, 3),
            new SkillLvl(136, 3),
            new SkillLvl(150, 3),
            new SkillLvl(154, 3),
            new SkillLvl(223, 1),
            new SkillLvl(230, 1),
            new SkillLvl(316, 1)
    ));
    public static List<Integer> FB_2 = new ArrayList<>(Arrays.asList(
            253,
            257,
            115,
            116,
            117,
            118,
            119,
            120,
            255,
            241,

            122,
            123,
            124,
            125,
            126,
            127,
            254,
            258,
            260,
            261,
            262, 263, 269, 270,
            271
    ));
    public static List<SkillLvl> FB_1_2 = new ArrayList<>(Arrays.asList(
            new SkillLvl(208, 9),
            new SkillLvl(2, 20),
            new SkillLvl(1, 20),
            new SkillLvl(17, 20),
            new SkillLvl(209, 9))
    );
    public static List<Integer> FB_1 = new ArrayList<>(Arrays.asList(
            468,
            22,
            25,
            33,
            29,
            68,
            38,
            46,
            37,
            58,
            66,
            75,
            7
    ));

    public static List<SkillLvl> FS_3 = new ArrayList<>(Arrays.asList(
            new SkillLvl(40, 10),
            new SkillLvl(41, 10),
            new SkillLvl(42, 8),
            new SkillLvl(51, 8),
            new SkillLvl(53, 9),
            new SkillLvl(54, 9),
            new SkillLvl(133, 3),
            new SkillLvl(136, 3),
            new SkillLvl(150, 3),
            new SkillLvl(154, 3),
            new SkillLvl(223, 1),
            new SkillLvl(230, 1),
            new SkillLvl(250, 1),
            new SkillLvl(468, 1)
    ));
    public static List<Integer> FS_2 = new ArrayList<>(Arrays.asList(
            157,
            248,
            158,
            115,
            116,
            117,
            118,
            119,
            120,
            122,
            123,
            124,
            125,
            126,
            127,
            249,
            159,
            250,
            251,
            224,
            225
    ));
    public static List<SkillLvl> FS_1_2 = new ArrayList<>(Arrays.asList(
            new SkillLvl(208, 9),
            new SkillLvl(2, 20),
            new SkillLvl(1, 20),
            new SkillLvl(11, 20),
            new SkillLvl(209, 9)
    ));
    public static List<Integer> FS_1 = new ArrayList<>(Arrays.asList(
            13,
            22,
            25,
            32,
            68,
            38,
            71,
            45,
            469,
            468,
            36,
            57,
            65,
            6,
            74
    ));

    public static List<Integer> FA_BOOKS = new ArrayList<>(Arrays.asList(
            470, 471, 19, 334
    ));
    public static List<Integer> BL_BOOKS = new ArrayList<>(Arrays.asList(
            472, 473, 21, 201
    ));
    public static List<Integer> FS_BOOKS = new ArrayList<>(Arrays.asList(
            15, 335
    ));
    public static List<Integer> FB_BOOKS = new ArrayList<>(Arrays.asList(
            14, 268, 336
    ));
    public static List<Integer> WI_BOOKS = new ArrayList<>(Arrays.asList(
            18, 239
    ));
    public static List<Integer> WA_BOOKS = new ArrayList<>(Arrays.asList(
            26, 222, 200
    ));
}

