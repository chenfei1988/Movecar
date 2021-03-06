package ittiger.indexlist;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式实现
 * @author: laohu on 2016/12/20
 * @site: http://ittiger.cn
 */
public class IndexValueBus {
    private static volatile ittiger.indexlist.IndexValueBus sEntityBus;
    private EntityObservable mEntityObservable;

    private IndexValueBus() {

        mEntityObservable = new EntityObservable();
    }

    public static ittiger.indexlist.IndexValueBus getInstance() {

        if(sEntityBus == null) {
            synchronized (ittiger.indexlist.IndexValueBus.class) {
                if(sEntityBus == null) {
                    sEntityBus = new ittiger.indexlist.IndexValueBus();
                }
            }
        }
        return sEntityBus;
    }

    public void addObserver(Observer observer) {

        mEntityObservable.addObserver(observer);
    }

    public void clear() {

        mEntityObservable.deleteObservers();
    }

    public void notifyDataSetChanged(List<String> indexValue) {

        mEntityObservable.observaleChanged();
        mEntityObservable.notifyObservers(indexValue);
    }

    public static class EntityObservable extends Observable {

        public void observaleChanged() {

            this.setChanged();
        }
    }
}
