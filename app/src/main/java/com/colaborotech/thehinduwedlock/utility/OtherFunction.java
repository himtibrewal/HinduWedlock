package com.colaborotech.thehinduwedlock.utility;


import com.colaborotech.thehinduwedlock.models.DataModel;
import com.colaborotech.thehinduwedlock.models.MultipleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by him on 11-Jun-17.
 */

public class OtherFunction {

    public static List<MultipleModel> returnMultipleModelArrayList(List<?> objects, String selectedPos) {
        String s[] = selectedPos.split(",");
        List<MultipleModel> multipleModelList = new ArrayList<>();
        a:
        for (int i = 0; i < objects.size(); i++) {
            Object object = objects.get(i);
            DataModel religionModel = (DataModel) object;
            for (int j = 0; j < s.length; j++) {
                if (s[j].equalsIgnoreCase(religionModel.get_id() + "")) {
                    multipleModelList.add(new MultipleModel(object, true));
                    continue a;
                }
            }
            multipleModelList.add(new MultipleModel(object, false));
        }
        return multipleModelList;
    }

}
