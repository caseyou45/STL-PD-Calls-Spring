package com.stlpd.util;

import java.util.HashMap;

public class NeighborhoodMap {
    private HashMap<Integer, String> neighborhoodMap;

    public NeighborhoodMap() {
        neighborhoodMap = new HashMap<>();
        neighborhoodMap.put(1, "Carondelet");
        neighborhoodMap.put(2, "Patch");
        neighborhoodMap.put(3, "Holly Hills");
        neighborhoodMap.put(4, "Boulevard Heights");
        neighborhoodMap.put(5, "Bevo Mill");
        neighborhoodMap.put(6, "Princeton Heights");
        neighborhoodMap.put(7, "Southampton");
        neighborhoodMap.put(8, "St. Louis Hills");
        neighborhoodMap.put(9, "Lindenwood Park");
        neighborhoodMap.put(10, "Ellendale");
        neighborhoodMap.put(11, "Clifton Heights");
        neighborhoodMap.put(12, "The Hill");
        neighborhoodMap.put(13, "Southwest Garden");
        neighborhoodMap.put(14, "Northampton");
        neighborhoodMap.put(15, "Tower Grove South");
        neighborhoodMap.put(16, "Dutchtown");
        neighborhoodMap.put(17, "Mount Pleasant");
        neighborhoodMap.put(18, "Marine Villa");
        neighborhoodMap.put(19, "Gravois Park");
        neighborhoodMap.put(20, "Kosciusko");
        neighborhoodMap.put(21, "Soulard");
        neighborhoodMap.put(22, "Benton Park");
        neighborhoodMap.put(23, "McKinley Heights");
        neighborhoodMap.put(24, "Fox Park");
        neighborhoodMap.put(25, "Tower Grove East");
        neighborhoodMap.put(26, "Compton Heights");
        neighborhoodMap.put(27, "Shaw");
        neighborhoodMap.put(28, "Botanical Heights");
        neighborhoodMap.put(29, "Tiffany");
        neighborhoodMap.put(30, "Benton Park West");
        neighborhoodMap.put(31, "The Gate District");
        neighborhoodMap.put(32, "Lafayette Square");
        neighborhoodMap.put(33, "Peabody Darst Webbe");
        neighborhoodMap.put(34, "LaSalle Park");
        neighborhoodMap.put(35, "Downtown");
        neighborhoodMap.put(36, "Downtown West");
        neighborhoodMap.put(37, "Midtown");
        neighborhoodMap.put(38, "Central West End");
        neighborhoodMap.put(39, "Forest Park South East");
        neighborhoodMap.put(40, "Kings Oak");
        neighborhoodMap.put(41, "Cheltenham");
        neighborhoodMap.put(42, "Clayton-Tamm");
        neighborhoodMap.put(43, "Franz Park");
        neighborhoodMap.put(44, "Hi-Pointe");
        neighborhoodMap.put(45, "Wydown Skinker");
        neighborhoodMap.put(46, "Skinker DeBaliviere");
        neighborhoodMap.put(47, "DeBaliviere Place");
        neighborhoodMap.put(48, "West End");
        neighborhoodMap.put(49, "Visitation Park");
        neighborhoodMap.put(50, "Wells Goodfellow");
        neighborhoodMap.put(51, "Academy");
        neighborhoodMap.put(52, "Kingsway West");
        neighborhoodMap.put(53, "Fountain Park");
        neighborhoodMap.put(54, "Lewis Place");
        neighborhoodMap.put(55, "Kingsway East");
        neighborhoodMap.put(56, "Greater Ville");
        neighborhoodMap.put(57, "The Ville");
        neighborhoodMap.put(58, "Vandeventer");
        neighborhoodMap.put(59, "Jeff Vanderlou");
        neighborhoodMap.put(60, "St. Louis Place");
        neighborhoodMap.put(61, "Carr Square");
        neighborhoodMap.put(62, "Columbus Square");
        neighborhoodMap.put(63, "Old North St. Louis");
        neighborhoodMap.put(64, "Near North Riverfront");
        neighborhoodMap.put(65, "Hyde Park");
        neighborhoodMap.put(66, "College Hill");
        neighborhoodMap.put(67, "Fairground Neighborhood");
        neighborhoodMap.put(68, "O'Fallon");
        neighborhoodMap.put(69, "Penrose");
        neighborhoodMap.put(70, "Mark Twain I-70 Industrial");
        neighborhoodMap.put(71, "Mark Twain");
        neighborhoodMap.put(72, "Walnut Park East");
        neighborhoodMap.put(73, "North Pointe");
        neighborhoodMap.put(74, "Baden");
        neighborhoodMap.put(75, "Riverview");
        neighborhoodMap.put(76, "Walnut Park West");
        neighborhoodMap.put(77, "Covenant Blu-Grand Center");
        neighborhoodMap.put(78, "Hamilton Heights");
        neighborhoodMap.put(79, "North Riverfront");

    }

    public String getNeighborhood(int num) {
        if (neighborhoodMap.containsKey(num)) {
            return neighborhoodMap.get(num);
        }

        return "- - -";
    }
}
