package com.stlpd.service;

import java.util.Comparator;
import java.util.List;

import com.stlpd.dto.CallCountDTO;
import com.stlpd.enums.SortDirection;
import com.stlpd.enums.SortMethod;

public class CallCountDTOSorting {

    public static void sort(String sortMethodString, String sortDirectionString, List<CallCountDTO> callCountDTOs) {

        SortMethod sortMethod = SortMethod.fromString(sortMethodString);
        SortDirection sortDirection = SortDirection.fromString(sortDirectionString);

        if (SortMethod.DATETIME.equals(sortMethod)) {
            if (SortDirection.ASC.equals(sortDirection)) {
                callCountDTOs.sort(Comparator.comparing(CallCountDTO::getCallDatetime));
            } else {
                callCountDTOs.sort(Comparator.comparing(CallCountDTO::getCallDatetime).reversed());

            }
        } else if (SortMethod.LOCATION.equals(sortMethod)) {
            if (SortDirection.ASC.equals(sortDirection)) {
                callCountDTOs.sort(Comparator.comparing(CallCountDTO::getCallLocation));
            } else {
                callCountDTOs.sort(Comparator.comparing(CallCountDTO::getCallLocation).reversed());
            }
        } else if (SortMethod.TYPE.equals(sortMethod)) {
            if (SortDirection.ASC.equals(sortDirection)) {
                callCountDTOs.sort(Comparator.comparing(CallCountDTO::getCallType));
            } else {
                callCountDTOs.sort(Comparator.comparing(CallCountDTO::getCallType).reversed());

            }
        }

    }

}
