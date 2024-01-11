package com.stlpd.service;

import java.util.Comparator;
import java.util.List;

import com.stlpd.dto.DisplayDTO;
import com.stlpd.enums.SortDirection;
import com.stlpd.enums.SortMethod;

public class DTOSorting {

    public static void sort(String sortMethodString, String sortDirectionString, List<DisplayDTO> displayDTOs) {

        SortMethod sortMethod = SortMethod.fromString(sortMethodString);
        SortDirection sortDirection = SortDirection.fromString(sortDirectionString);

        if (SortMethod.DATETIME.equals(sortMethod)) {
            if (SortDirection.ASC.equals(sortDirection)) {
                displayDTOs.sort(Comparator.comparing(DisplayDTO::getDatetime));
            } else {
                displayDTOs.sort(Comparator.comparing(DisplayDTO::getDatetime).reversed());

            }
        } else if (SortMethod.LOCATION.equals(sortMethod)) {
            if (SortDirection.ASC.equals(sortDirection)) {
                displayDTOs.sort(Comparator.comparing(DisplayDTO::getLocation));
            } else {
                displayDTOs.sort(Comparator.comparing(DisplayDTO::getLocation).reversed());
            }
        } else if (SortMethod.TYPE.equals(sortMethod)) {
            if (SortDirection.ASC.equals(sortDirection)) {
                displayDTOs.sort(Comparator.comparing(DisplayDTO::getOffense));
            } else {
                displayDTOs.sort(Comparator.comparing(DisplayDTO::getOffense).reversed());

            }
        }

    }

}
