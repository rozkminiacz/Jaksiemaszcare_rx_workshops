package com.rozkmin.recyclerdatabindingshowcase;

import java.util.List;

/**
 * Created by jaroslawmichalik on 13.12.2017
 */

interface MainActivityContract {
    interface View {
        void addItem(Item item);

        void addItems(List<Item> itemList);

        void showError(Throwable throwable);
    }

    interface Presenter {
        void attach(View view);

        void detach();
    }
}
