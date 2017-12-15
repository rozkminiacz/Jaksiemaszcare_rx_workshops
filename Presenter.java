package com.rozkmin.recyclerdatabindingshowcase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jaroslawmichalik on 13.12.2017
 */

public class Presenter implements MainActivityContract.Presenter {
    private MainActivityContract.View view;
    private Disposable disposable;

    @Override
    public void attach(MainActivityContract.View view) {
        this.view = view;
        loadData();
    }


    private void loadData() {
        disposable = Observable.fromIterable(getItems())
                .subscribeOn(Schedulers.io())
                .doOnNext(view::addItem)
                .onErrorReturn(throwable -> new Item("", "", "", 0, ""))
                .doOnError(view::showError)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void detach() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        this.view = null;
    }

    private static List<Item> getItems() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"));
        list.add(new Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"));
        list.add(new Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"));
        list.add(new Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"));
        list.add(new Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"));
        list.add(new Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"));
        list.add(new Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"));
        list.add(new Item(UUID.randomUUID().toString(), "Frank", "Overtrees", 1, "Team Gnome"));
        return list;
    }
}
